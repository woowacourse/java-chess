package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final Type type;

    protected Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isRightMovement(final Position from, final Position to, final boolean isEmptyTarget);

    public abstract double getScore();

    public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isJumpable() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public Color color() {
        return color;
    }

    public Type type() {
        return type;
    }

    public String getColor() {
        return color.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
