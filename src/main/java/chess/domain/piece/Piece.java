package chess.domain.piece;

import chess.domain.Square;
import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Square square;

    public Piece(final Color color, final Square square) {
        this.color = color;
        this.square = square;
    }

    @Override
    public boolean equals(final Object o) {
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
