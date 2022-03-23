package chess.domain.piece;

import java.util.Objects;

public abstract class FullPiece implements Piece {

    private final Color color;

    public FullPiece(final Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FullPiece fullPiece = (FullPiece) o;
        return color == fullPiece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public abstract String getName();
}
