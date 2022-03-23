package chess.domain.piece;

import java.util.Objects;

public abstract class Piece {

    private final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public final Color getColor() {
        return color;
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
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
