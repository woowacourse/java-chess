package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final String name;

    protected Piece(final Color color, final String name) {
        this.color = color;
        this.name = decideName(name);
    }

    private String decideName(final String name) {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    public abstract boolean isMovable(final Position from, final Position to,
        final boolean isEmptyTarget);

    public boolean isSameColor(final Color color) {
        return this.color.equals(color);
    }

    public boolean isJumpable() {
        return false;
    }

    public boolean isKing() {
        return false;
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
        return Objects.equals(name, piece.name) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    public String getName() {
        return name;
    }

}
