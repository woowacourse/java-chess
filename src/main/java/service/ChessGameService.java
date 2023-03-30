package service;

import dao.GameTurnDao;
import dao.PieceDao;
import domain.chessGame.ChessBoard;
import domain.chessGame.ChessBoardGenerator;
import domain.chessGame.ScoreCalculator;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceMaker;
import domain.piece.PieceName;
import domain.position.Position;
import dto.ChessBoardStateDto;
import dto.ChessGameScoreDto;
import dto.GameTurnDto;
import dto.PieceDto;
import dto.PositionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameService {

    public static final String DOT = ".";

    private final PieceDao pieceDao;
    private final GameTurnDao gameTurnDao;

    public ChessGameService(PieceDao pieceDao, GameTurnDao gameTurnDao) {
        this.pieceDao = pieceDao;
        this.gameTurnDao = gameTurnDao;
    }

    public void move(List<String> commandInput) {
        ChessBoard chessBoard = findChessBoard();

        Position start = Position.of(commandInput.get(1));
        Position end = Position.of(commandInput.get(2));

        chessBoard.movePiece(start, end);

        if (!findChessBoard().isSameWith(chessBoard)) {
            pieceDao.updatePiece(new PositionDto(start), new PositionDto(end),
                    new PieceDto(chessBoard.getPieceByPosition(end), end));

            gameTurnDao.update(makeGameTurnDto(chessBoard));
        }
    }

    private ChessBoard findChessBoard() {
        List<PieceDto> pieceDtos = pieceDao.findAllPieces();

        if (pieceDtos.size() == 0) {
            ChessBoardGenerator generator = new ChessBoardGenerator();
            return new ChessBoard(generator.generate());
        }

        Map<Position, Piece> loadBoard = new HashMap<>();
        
        for (PieceDto pieceDto : pieceDtos) {
            PieceName pieceName = PieceName.of(pieceDto.getName());
            Color pieceColor = Color.valueOf(pieceDto.getPieceColor());
            Position position = Position.of(pieceDto.getRow(), pieceDto.getColumn());

            Piece piece = makePiece(pieceName, pieceColor);

            loadBoard.put(position, piece);
        }

        GameTurnDto gameTurnDto = gameTurnDao.find();
        Color turnOfColor = Color.valueOf(gameTurnDto.getTurnOfColor());
        
        return new ChessBoard(loadBoard, turnOfColor);
    }

    private Piece makePiece(PieceName pieceName, Color pieceColor) {
        PieceMaker pieceMaker = PieceMaker.from(pieceName);
        return pieceMaker.make(pieceColor);
    }

    private GameTurnDto makeGameTurnDto(ChessBoard chessBoard) {
        return new GameTurnDto(chessBoard.getTurnOfColor().name());
    }

    public ChessGameScoreDto calculateScore() {
        ChessBoard chessBoard = findChessBoard();
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard.getBlackPieces(), chessBoard.getWhitePieces());
        return new ChessGameScoreDto(scoreCalculator.getBlackScore(), scoreCalculator.getWhiteScore());
    }

    public ChessBoardStateDto makeChessBoardState() {
        Map<Position, Piece> chessBoard = findChessBoard().getChessBoard();
        List<String> boardState = new ArrayList<>();

        for (List<Position> rowPositions : Position.getAllPositions()) {
            boardState.add(makeRowState(chessBoard, rowPositions));
        }
        return new ChessBoardStateDto(boardState);
    }

    private String makeRowState(Map<Position, Piece> chessBoard, List<Position> rowPositions) {
        return rowPositions.stream()
                .map(position -> convertPieceNameOrDot(chessBoard, position))
                .collect(Collectors.joining());
    }

    private String convertPieceNameOrDot(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            return chessBoard.get(position).getName();
        }
        return DOT;
    }

    public boolean isGameEnded() {
        return findChessBoard().isGameEnded();
    }

    public void deleteChessBoard() {
        pieceDao.deleteAllPieces();
        gameTurnDao.delete();
    }
}
