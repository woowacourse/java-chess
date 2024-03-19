package domain;

import java.util.Objects;

public class Square {
    private final PieceType pieceType;
    private final Position position;

    public Square(final PieceType pieceType, final Position position) {
        this.pieceType = pieceType;
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
        final Square square = (Square) o;
        return Objects.equals(position, square.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
