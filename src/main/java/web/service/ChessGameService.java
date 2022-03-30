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
import web.dao.ChessBoardDao;
import web.dao.GameStatusDao;
import web.dao.ScoreDao;
import web.dto.GameStatus;
import web.dto.PieceDTO;

public class ChessGameService {

    private final ChessBoardDao chessBoardDao;
    private final ScoreDao scoreDao;
    private final GameStatusDao gameStatusDao;

    public ChessGameService(ChessBoardDao chessBoardDao, ScoreDao scoreDao, GameStatusDao gameStatusDao) {
        this.chessBoardDao = chessBoardDao;
        this.scoreDao = scoreDao;
        this.gameStatusDao = gameStatusDao;
    }

    public void move(Movement movement) {
        ChessBoard chessBoard = chessBoardDao.findChessBoard();
        chessBoard.move(movement.getFrom(), movement.getTo());
        updateChessGame(chessBoard, movement);
    }

    private void updateChessGame(ChessBoard chessBoard, Movement movement) {
        updateChessBoard(chessBoard, movement);
        updateScores(chessBoard);
        if (chessBoard.isFinished()) {
            updateGameStatus(chessBoard);
        }
    }

    private void updateChessBoard(ChessBoard chessBoard, Movement movement) {
        Map<Position, Piece> board = chessBoard.getBoard();
        chessBoardDao.deletePieceByPosition(movement.getFrom());
        chessBoardDao.updatePiece(new PieceDTO(movement.getTo(), board.get(movement.getTo())));
        chessBoardDao.updateCurrentColor(chessBoard.getCurrentColor());
    }

    private void updateScores(ChessBoard chessBoard) {
        scoreDao.updateScoreByColor(chessBoard.getScore(Color.BLACK), Color.BLACK);
        scoreDao.updateScoreByColor(chessBoard.getScore(Color.WHITE), Color.WHITE);
    }

    private void updateGameStatus(ChessBoard chessBoard) {
        gameStatusDao.updateGameStatus(GameStatus.FINISHED);
        gameStatusDao.saveWinner(chessBoard.getWinner());
    }

    public List<PieceDTO> queryPieces() {
        return chessBoardDao.findPieces();
    }

    public Score queryScoreByColor(Color color) {
        return scoreDao.findScoreByColor(color);
    }

    public void resetGame() {
        deleteChessGame();
        resetChessGame();
    }

    private void deleteChessGame() {
        chessBoardDao.deleteAll();
        scoreDao.deleteAll();
        gameStatusDao.deleteAll();
    }

    private void resetChessGame() {
        resetChessBoard();
        resetScores();
        resetGameStatus();
    }

    private void resetChessBoard() {
        chessBoardDao.savePieces(createPieces());
        chessBoardDao.saveCurrentColor(Color.WHITE);
    }

    private List<PieceDTO> createPieces() {
        return Stream.concat(createWhitePieces().stream(), createBlackPieces().stream())
                .collect(Collectors.toList());
    }

    private static List<PieceDTO> createWhitePieces() {
        return List.of(
                new PieceDTO(new Position(A, ONE), new Rook(Color.WHITE)),
                new PieceDTO(new Position(B, ONE), new Knight(Color.WHITE)),
                new PieceDTO(new Position(C, ONE), new Bishop(Color.WHITE)),
                new PieceDTO(new Position(D, ONE), new Queen(Color.WHITE)),
                new PieceDTO(new Position(E, ONE), new King(Color.WHITE)),
                new PieceDTO(new Position(F, ONE), new Bishop(Color.WHITE)),
                new PieceDTO(new Position(G, ONE), new Knight(Color.WHITE)),
                new PieceDTO(new Position(H, ONE), new Rook(Color.WHITE)),
                new PieceDTO(new Position(A, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(B, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(C, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(D, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(E, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(F, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(G, TWO), new Pawn(Color.WHITE)),
                new PieceDTO(new Position(H, TWO), new Pawn(Color.WHITE)));
    }

    private static List<PieceDTO> createBlackPieces() {
        return List.of(
                new PieceDTO(new Position(A, EIGHT), new Rook(Color.BLACK)),
                new PieceDTO(new Position(B, EIGHT), new Knight(Color.BLACK)),
                new PieceDTO(new Position(C, EIGHT), new Bishop(Color.BLACK)),
                new PieceDTO(new Position(D, EIGHT), new Queen(Color.BLACK)),
                new PieceDTO(new Position(E, EIGHT), new King(Color.BLACK)),
                new PieceDTO(new Position(F, EIGHT), new Bishop(Color.BLACK)),
                new PieceDTO(new Position(G, EIGHT), new Knight(Color.BLACK)),
                new PieceDTO(new Position(H, EIGHT), new Rook(Color.BLACK)),
                new PieceDTO(new Position(A, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(B, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(C, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(D, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(E, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(F, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(G, SEVEN), new Pawn(Color.BLACK)),
                new PieceDTO(new Position(H, SEVEN), new Pawn(Color.BLACK)));
    }

    private void resetScores() {
        scoreDao.saveScoreByColor(new Score(new BigDecimal("38.0")), Color.BLACK);
        scoreDao.saveScoreByColor(new Score(new BigDecimal("38.0")), Color.WHITE);
    }

    private void resetGameStatus() {
        gameStatusDao.saveGameStatus(GameStatus.RUNNING);
    }

    public boolean isGameFinished() {
        return gameStatusDao.findGameStatus() == GameStatus.FINISHED;
    }

    public Color findWinner() {
        return gameStatusDao.findWinner();
    }
}
