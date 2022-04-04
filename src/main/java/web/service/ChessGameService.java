package web.service;

import static chess.position.File.A;
import static chess.position.File.B;
import static chess.position.File.C;
import static chess.position.File.D;
import static chess.position.File.E;
import static chess.position.File.F;
import static chess.position.File.G;
import static chess.position.File.H;
import static chess.position.Rank.EIGHT;
import static chess.position.Rank.ONE;
import static chess.position.Rank.SEVEN;
import static chess.position.Rank.TWO;
import static java.util.stream.Collectors.toMap;

import chess.ChessBoard;
import chess.Score;
import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import web.controller.Movement;
import web.dao.ChessGameDao;
import web.dao.PieceDao;
import web.dto.GameStatus;
import web.dto.PieceDto;

public class ChessGameService {

    private final PieceDao pieceDao;
    private final ChessGameDao chessGameDao;

    public ChessGameService(PieceDao pieceDao, ChessGameDao chessGameDao) {
        this.pieceDao = pieceDao;
        this.chessGameDao = chessGameDao;
    }

    public void move(int chessGameId, Movement movement) {
        ChessBoard chessBoard = createChessBoard(chessGameId);
        chessBoard.move(movement.getFrom(), movement.getTo());
        updateChessGame(chessGameId, chessBoard, movement);
    }

    private ChessBoard createChessBoard(int chessGameId) {
        List<PieceDto> pieces = pieceDao.findPieces(chessGameId);
        Color currentColor = chessGameDao.findCurrentColor(chessGameId);
        return new ChessBoard(createBoard(pieces), currentColor);
    }

    private Map<Position, Piece> createBoard(List<PieceDto> pieces) {
        return pieces.stream()
                .collect(toMap(PieceDto::getPosition, PieceDto::createPiece));
    }

    private void updateChessGame(int chessGameId, ChessBoard chessBoard, Movement movement) {
        updatePieces(chessGameId, chessBoard, movement);
        updateCurrentColor(chessGameId, chessBoard);
        updateScores(chessGameId, chessBoard);
        updateGameStatus(chessGameId, chessBoard);
    }

    private void updatePieces(int chessGameId, ChessBoard chessBoard, Movement movement) {
        Map<Position, Piece> board = chessBoard.getBoard();
        pieceDao.deletePieceByPosition(chessGameId, movement.getFrom());
        pieceDao.deletePieceByPosition(chessGameId, movement.getTo());
        pieceDao.savePiece(chessGameId, new PieceDto(movement.getTo(), board.get(movement.getTo())));
    }

    private void updateCurrentColor(int chessGameId, ChessBoard chessBoard) {
        chessGameDao.updateCurrentColor(chessGameId, chessBoard.getCurrentColor());
    }

    private void updateScores(int chessGameId, ChessBoard chessBoard) {
        chessGameDao.updateScoreByColor(chessGameId, chessBoard.getScore(Color.BLACK), Color.BLACK);
        chessGameDao.updateScoreByColor(chessGameId, chessBoard.getScore(Color.WHITE), Color.WHITE);
    }

    private void updateGameStatus(int chessGameId, ChessBoard chessBoard) {
        if (chessBoard.isFinished()) {
            chessGameDao.updateGameStatus(chessGameId, GameStatus.FINISHED);
            chessGameDao.updateWinner(chessGameId, chessBoard.getWinner());
        }
    }

    public void prepareNewChessGame(int chessGameId) {
        pieceDao.deleteAll(chessGameId);
        pieceDao.savePieces(chessGameId, createPieces());
        Score initialScore = new Score(new BigDecimal("38.0"));
        chessGameDao.updateChessGame(chessGameId, GameStatus.RUNNING, Color.WHITE, initialScore, initialScore);
    }

    private List<PieceDto> createPieces() {
        return Stream.concat(createWhitePieces().stream(), createBlackPieces().stream())
                .collect(Collectors.toList());
    }

    private static List<PieceDto> createWhitePieces() {
        return List.of(
                new PieceDto(new Position(A, ONE), new Rook(Color.WHITE)),
                new PieceDto(new Position(B, ONE), new Knight(Color.WHITE)),
                new PieceDto(new Position(C, ONE), new Bishop(Color.WHITE)),
                new PieceDto(new Position(D, ONE), new Queen(Color.WHITE)),
                new PieceDto(new Position(E, ONE), new King(Color.WHITE)),
                new PieceDto(new Position(F, ONE), new Bishop(Color.WHITE)),
                new PieceDto(new Position(G, ONE), new Knight(Color.WHITE)),
                new PieceDto(new Position(H, ONE), new Rook(Color.WHITE)),
                new PieceDto(new Position(A, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(B, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(C, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(D, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(E, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(F, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(G, TWO), new Pawn(Color.WHITE)),
                new PieceDto(new Position(H, TWO), new Pawn(Color.WHITE)));
    }

    private static List<PieceDto> createBlackPieces() {
        return List.of(
                new PieceDto(new Position(A, EIGHT), new Rook(Color.BLACK)),
                new PieceDto(new Position(B, EIGHT), new Knight(Color.BLACK)),
                new PieceDto(new Position(C, EIGHT), new Bishop(Color.BLACK)),
                new PieceDto(new Position(D, EIGHT), new Queen(Color.BLACK)),
                new PieceDto(new Position(E, EIGHT), new King(Color.BLACK)),
                new PieceDto(new Position(F, EIGHT), new Bishop(Color.BLACK)),
                new PieceDto(new Position(G, EIGHT), new Knight(Color.BLACK)),
                new PieceDto(new Position(H, EIGHT), new Rook(Color.BLACK)),
                new PieceDto(new Position(A, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(B, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(C, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(D, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(E, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(F, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(G, SEVEN), new Pawn(Color.BLACK)),
                new PieceDto(new Position(H, SEVEN), new Pawn(Color.BLACK)));
    }
}
