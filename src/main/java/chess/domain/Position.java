package chess.domain;

import java.util.Objects;

public class Position {
    private final Coordinate x;
    private final Coordinate y;

    public Position(final Coordinate x, final Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public Direction distance(Position position) {
        return Direction.valueOf(x.subtract(position.x), y.subtract(position.y));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
