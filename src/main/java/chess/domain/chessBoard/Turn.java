package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class Turn {

    private static final Turn whiteTurn = new Turn(Color.WHITE);
    private static final Turn blackTurn = new Turn(Color.BLACK);
    private static final Turn noneTurn = new Turn(Color.EMPTY);

    private final Color color;

    private Turn(Color color) {
        this.color = color;
    }

    public static Turn notPlayingGame() {
        return noneTurn;
    }

    public static Turn firstTurn() {
        return whiteTurn;
    }

    public Turn oppositeTurn() {
        if (color == Color.BLACK) {
            return whiteTurn;
        }
        return blackTurn;
    }

    public boolean isActive() {
        return color != Color.EMPTY;
    }

    public boolean isValidTurn(Piece piece) {
        return piece.isSameColor(color);
    }
}
