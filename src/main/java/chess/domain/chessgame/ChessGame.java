package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.status.GameStatus;

import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;

    public ChessGame(final ChessBoard chessBoard, final GameStatus gameStatus) {
        this.chessBoard = chessBoard;
        this.gameStatus = gameStatus;
    }

    public ChessGame() {
        this(createNewChessBoard(), GameStatus.getInitialStatus());
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
        return gameStatus.getWinner();
    }

    public Color getTurn() {
        return gameStatus.getTurn();
    }
}
