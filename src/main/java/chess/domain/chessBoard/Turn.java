package chess.domain.chessBoard;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class Turn {

    private final Color color;

    public Turn(Color color) {
        this.color = color;
    }

    public Turn oppositeTurn() {
        if (color == Color.BLACK) {
            return new Turn(Color.WHITE);
        }
        return new Turn(Color.BLACK);
    }

    public boolean isActive() {
        return color != Color.EMPTY;
    }

    public boolean isValidTurn(Piece piece) {
        return piece.isSameColor(color);
    }
}
