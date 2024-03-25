package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public abstract class Piece {
    protected final Position position;
    protected final Color color;

    protected Piece(final Position position, final Color color) {
        this.position = position;
        this.color = color;
    }

    public abstract Set<Position> findPathTo(final Position destination);

    public boolean isSameColor(final Color otherColor) {
        return color == otherColor;
    }

    public boolean isOppositeColor(final Piece other) {
        if (color == Color.WHITE) {
            return other.color == Color.BLACK;
        }
        if (color == Color.BLACK) {
            return other.color == Color.WHITE;
        }
        return false;
    }

    public abstract Piece update(final Position destination);

    public abstract PieceType pieceType();
}
