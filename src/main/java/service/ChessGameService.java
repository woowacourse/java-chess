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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final GameTurnDao gameTurnDao;

    public ChessGameService(PieceDao pieceDao, GameTurnDao gameTurnDao) {
        this.pieceDao = pieceDao;
        this.gameTurnDao = gameTurnDao;
    }

    public void move(List<String> commandInput) {
        Position start = Position.of(commandInput.get(1));
        Position end = Position.of(commandInput.get(2));

        ChessBoard chessBoard = findChessBoard();
        chessBoard.movePiece(start, end);

        updateMoveResult(start, end, chessBoard);
    }

    private void updateMoveResult(Position start, Position end, ChessBoard ChangedChessBoard) {
        if (!findChessBoard().isSameWith(ChangedChessBoard)) {
            pieceDao.updatePiece(new PositionDto(start), new PositionDto(end),
                    new PieceDto(ChangedChessBoard.getPieceByPosition(end), end));

            gameTurnDao.update(makeGameTurnDto(ChangedChessBoard));
        }
    }

    private GameTurnDto makeGameTurnDto(ChessBoard chessBoard) {
        return new GameTurnDto(chessBoard.getTurnOfColor().name());
    }

    public ChessBoardStateDto makeChessBoardState() {
        return new ChessBoardStateDto(findChessBoard());
    }

    public ChessGameScoreDto calculateScore() {
        ChessBoard chessBoard = findChessBoard();
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard.getBlackPieces(), chessBoard.getWhitePieces());
        return new ChessGameScoreDto(scoreCalculator);
    }

    public boolean isGameEnded() {
        return findChessBoard().isGameEnded();
    }

    public void deleteChessBoard() {
        pieceDao.deleteAllPieces();
        gameTurnDao.delete();
    }

    private ChessBoard findChessBoard() {
        List<PieceDto> pieceDtos = pieceDao.findAllPieces();

        if (pieceDtos.size() == 0) {
            ChessBoardGenerator generator = new ChessBoardGenerator();
            return new ChessBoard(generator.generate());
        }

        return new ChessBoard(makeLoadBoard(pieceDtos), findColorOfTurn());
    }

    private Map<Position, Piece> makeLoadBoard(List<PieceDto> pieceDtos) {
        Map<Position, Piece> loadBoard = new HashMap<>();

        for (PieceDto pieceDto : pieceDtos) {
            PieceName pieceName = PieceName.of(pieceDto.getName());
            Color pieceColor = Color.valueOf(pieceDto.getPieceColor());
            Position position = Position.of(pieceDto.getRow(), pieceDto.getColumn());
            Piece piece = makePiece(pieceName, pieceColor);

            loadBoard.put(position, piece);
        }
        return loadBoard;
    }

    private Piece makePiece(PieceName pieceName, Color pieceColor) {
        PieceMaker pieceMaker = PieceMaker.from(pieceName);
        return pieceMaker.make(pieceColor);
    }

    private Color findColorOfTurn() {
        GameTurnDto gameTurnDto = gameTurnDao.find();
        return Color.valueOf(gameTurnDto.getTurnOfColor());
    }
}
