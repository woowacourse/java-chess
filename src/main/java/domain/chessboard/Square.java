package domain.chessboard;

import domain.position.Position;
import java.util.Objects;

public class Square {
    private final Position position;

    public Square(final Position position) {
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
