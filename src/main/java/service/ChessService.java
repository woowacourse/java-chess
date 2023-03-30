package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import dao.ChessDao;
import domain.PieceNameConverter;
import domain.PieceToScoreConverter;
import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;
import dto.BoardResponseDto;
import dto.CommandRequestDto;
import dto.ScoreResponseDto;

public class ChessService {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final ChessBoard chessBoard;
    private final ChessDao chessDao;
    private Camp currentCamp;
    private boolean ongoing;

    public ChessService() {
        this.chessBoard = new ChessBoard();
        this.chessDao = new ChessDao();
        this.currentCamp = Camp.WHITE;
        PieceNameConverter.init();
        PieceToScoreConverter.init();
    }

    public void start() {
        if (hasData())
            return;

        if (hasNoData())
            return;

        throw new IllegalStateException("이미 게임이 실행중입니다.");
    }

    private boolean hasData() {
        if (!ongoing && chessDao.hasData()) {
            return loadData();
        }
        return false;
    }

    private boolean loadData() {
        for (Map.Entry<Square, Piece> squareAndPiece : chessBoard.getBoard().entrySet()) {
            chessBoard.getBoard()
                .put(squareAndPiece.getKey(),
                    PieceNameConverter.convert(chessDao.select(squareAndPiece.getKey().toString())));
        }
        chessDao.update(convert(chessBoard));
        currentCamp = Camp.valueOf(chessDao.selectCamp());
        ongoing = true;
        return true;
    }

    private boolean hasNoData() {
        if (!ongoing) {
            chessBoard.initialize();
            chessDao.save(convert(chessBoard));
            chessDao.saveCamp(currentCamp.name());
            ongoing = true;
            return true;
        }
        return false;
    }

    private Map<String, String> convert(ChessBoard chessBoard) {
        Map<Square, Piece> board = chessBoard.getBoard();
        return board.keySet()
            .stream()
            .collect(Collectors.toMap(Square::toString, square -> PieceNameConverter.convert(board.get(square))));
    }

    public void end() {
        ongoing = false;
    }

    public void move(CommandRequestDto commandRequestDto) {
        if (ongoing) {
            Square currentSquare = convertToSquare(commandRequestDto.getCurrentSquareName());
            Square targetSquare = convertToSquare(commandRequestDto.getTargetSquareName());

            validateTurn(currentSquare);

            chessBoard.move(currentSquare, targetSquare);
            chessDao.update(convert(chessBoard));
            chessDao.updateCamp(currentCamp.name());
            currentCamp = currentCamp.fetchOppositeCamp();
            checkKingDead();


            return;
        }
        throw new IllegalStateException("게임을 먼저 실행해주세요.");
    }

    private void checkKingDead() {
        if (KingIsDead()) {
            ongoing = false;
            chessDao.delete();
        }
    }

    private boolean KingIsDead() {
        return chessBoard.getBoard()
            .values()
            .stream()
            .filter(piece -> PieceToScoreConverter.isKingOrEmpty(piece) && !piece.isEmpty())
            .count() != 2;
    }

    private Square convertToSquare(String squareName) {
        return new Square(File.find(squareName.charAt(FILE_INDEX)), Rank.find(squareName.charAt(RANK_INDEX)));
    }

    private void validateTurn(Square currentSquare) {
        if (!chessBoard.isCorrectCamp(currentCamp, currentSquare)) {
            throw new IllegalStateException(currentCamp.name() + "의 턴입니다.");
        }
    }

    public BoardResponseDto toBoardDto() {
        return new BoardResponseDto(chessBoard.getBoard());
    }

    public ScoreResponseDto toScoreDto() {
        Map<Camp, Double> score = calculateScore();
        return new ScoreResponseDto(score);
    }

    private Map<Camp, Double> calculateScore() {
        HashMap<Camp, Double> campAndScores = new HashMap<>();
        calculateScore(campAndScores, Camp.WHITE, Piece::isWhite);
        calculateScore(campAndScores, Camp.BLACK, Piece::isBlack);
        return campAndScores;
    }

    private void calculateScore(HashMap<Camp, Double> campAndScores, Camp camp, Predicate<Piece> predicate) {
        campAndScores.put(camp,
            chessBoard.getBoard()
                .values()
                .stream()
                .filter(predicate)
                .mapToDouble(PieceToScoreConverter::convert)
                .sum() - calculatePawnDisadvantage(predicate));
    }

    private double calculatePawnDisadvantage(Predicate<Piece> predicate) {
        double disadvantage = 0;
        Map<Integer, List<Square>> fileAndPieces = groupByFile(predicate);
        Map<Integer, Long> fileAndPawnCounts = countOverPawn(fileAndPieces);

        for (Long count : fileAndPawnCounts.values()) {
            disadvantage = calculateDisadvantage(disadvantage, count);
        }

        return disadvantage;
    }

    private Map<Integer, Long> countOverPawn(Map<Integer, List<Square>> collect) {
        return collect.entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()
                .stream()
                .filter(square -> PieceToScoreConverter.isPawn(chessBoard.getBoard().get(square)))
                .count() - 1));
    }

    private Map<Integer, List<Square>> groupByFile(Predicate<Piece> predicate) {
        return chessBoard.getBoard()
            .keySet()
            .stream()
            .filter(square -> predicate.test(chessBoard.getBoard().get(square)))
            .collect(Collectors.groupingBy(square -> square.toCoordinate().get(FILE_INDEX)));
    }

    private double calculateDisadvantage(double disadvantage, Long value) {
        if (value >= 1) {
            disadvantage -= 0.5 * value;
        }
        return disadvantage;
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
