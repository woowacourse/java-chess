package chess.piece;

import chess.Color;
import chess.Position;

import java.util.Map;

public abstract class Piece {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position start, Position end, Piece endPiece, Map<Position, Piece> pieces);

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isEmpty() {
        return color.isEmpty();
    }

    public boolean isDifferentColor(Color otherColor) {
        return color != otherColor;
    }
}
