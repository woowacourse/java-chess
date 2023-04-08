package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.status.GameStatus;

import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard) {
        this(chessBoard);
    }

    public ChessGame(final ChessBoard chessBoard, final GameStatus gameStatus) {
        this.chessBoard = chessBoard;
        this.gameStatus = gameStatus;
    }

    public void moveWithCapture(final Position from, final Position to) {
        gameStatus.validateMove(chessBoard, from, to);

        final Piece capturedPiece = chessBoard.moveWithCapture(from, to);

        gameStatus = gameStatus.nextStatus(chessBoard);
    }

    public boolean isGameOver() {
        return gameStatus.isGameOver();
    }

    public PlayerScore calculateScore(Color player) {
        final Map<Position, Piece> piecesOfPlayer = chessBoard.getPieces(player);

        return PlayerScore.from(piecesOfPlayer);
        return null;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Color getWinner() {
        return gameStatus.getWinner();
    }

    public Color getTurn() {
        return gameStatus.getTurn();
    }
}
