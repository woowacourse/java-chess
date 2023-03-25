package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import domain.PieceToScoreConverter;
import domain.PieceToStringConverter;
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
    private Camp currentCamp;
    private boolean ongoing;

    public ChessService() {
        this.chessBoard = new ChessBoard();
        this.currentCamp = Camp.WHITE;
        PieceToStringConverter.init();
        PieceToScoreConverter.init();
    }

    public void start() {
        if (!ongoing) {
            chessBoard.initialize();
            ongoing = true;
            return;
        }
        throw new IllegalStateException("이미 게임이 실행중입니다.");
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
            checkKingDead();
            currentCamp = currentCamp.fetchOppositeCamp();
            return;
        }
        throw new IllegalStateException("게임을 먼저 실행해주세요.");
    }

    private void checkKingDead() {
        if (chessBoard.getBoard()
            .values()
            .stream()
            .filter(piece -> PieceToScoreConverter.convert(piece) == 0 && !piece.isEmpty()).count() != 2) {
            ongoing = false;
        }
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
        campAndScores.put(camp, chessBoard.getBoard()
            .values()
            .stream()
            .filter(predicate)
            .mapToDouble(PieceToScoreConverter::convert)
            .sum() - calculatePawnDisadvantage(predicate));
    }

    private double calculatePawnDisadvantage(Predicate<Piece> predicate) {
        double disadvantage = 0;
        Map<Integer, List<Square>> collect = chessBoard.getBoard()
            .keySet()
            .stream()
            .filter(square -> predicate.test(chessBoard.getBoard().get(square)))
            .collect(Collectors.groupingBy(square -> square.toCoordinate().get(FILE_INDEX)));

        Map<Integer, Long> collect1 = collect.entrySet().stream().collect(
            Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue()
                    .stream()
                    .filter(square -> PieceToScoreConverter.convert(chessBoard.getBoard().get(square)) == 1)
                    .count() - 1
            )
        );

        for (Long value : collect1.values()) {
            if (value >= 1) {
                disadvantage -= 0.5 * value;
            }
        }

        return disadvantage;
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
