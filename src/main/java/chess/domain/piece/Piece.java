package chess.domain.piece;

import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final Position position;

    public Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
