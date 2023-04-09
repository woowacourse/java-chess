package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.status.GameStatus;

import java.util.List;

public class ChessGame {

    private final int id;
    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    private ChessGame(final int id, final ChessBoard chessBoard, final GameStatus gameStatus) {
        this.id = id;
        this.chessBoard = chessBoard;
        this.gameStatus = gameStatus;
    }

    public static ChessGame createNewChessGame(int id) {
        return new ChessGame(id, createNewChessBoard(), GameStatus.getInitialStatus());
    }

    public static ChessGame createChessGame(final int id, final ChessBoard chessBoard, final GameStatus gameStatus) {
        return new ChessGame(id, chessBoard, gameStatus);
    }

    private static ChessBoard createNewChessBoard() {
        return new ChessBoardFactory().createInitialBoard();
    }

    public void moveWithCapture(final Position from, final Position to) {
        gameStatus.validatePlayerTurn(chessBoard, from);

        final Piece capturedPiece = chessBoard.moveWithCapture(from, to);

        gameStatus = gameStatus.nextStatus(capturedPiece);
    }

    public boolean isGameOver() {
        return gameStatus.isGameOver();
    }

    public PlayerScore calculateScore(Color color) {
        final List<Piece> pieces = chessBoard.getPieces(color);
        return PlayerScore.from(pieces);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Color getWinner() {
        validateGameOver();

        return gameStatus.getWinner();
    }

    private void validateGameOver() {
        if (!isGameOver()) {
            throw new IllegalArgumentException("게임이 진행 중입니다");
        }
    }

    public Color getTurn() {
        return gameStatus.getTurn();
    }

    public int getId() {
        return id;
    }
}
