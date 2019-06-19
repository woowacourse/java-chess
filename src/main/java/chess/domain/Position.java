package chess.domain;

import java.util.Objects;

import static chess.domain.Direction.N;

public class Position {
    private final Coordinate x;
    private final Coordinate y;

    public Position(final Coordinate x, final Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public Direction direction(Position position) {
        return Direction.valueOf(x.subtract(position.x), y.subtract(position.y));
    }

    public int distance(Position target, Direction direction) {
        if (direction.isDiagonal()) {
            return distanceDiagonal(target);
        }
        return distanceCross(target);
    }

    private int distanceDiagonal(Position target) {
        return (Math.abs(x.subtract(target.x)) + Math.abs(y.subtract(target.y))) / 2;
    }

    private int distanceCross(Position target) {
        return Math.abs(x.subtract(target.x) + y.subtract(target.y));
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
