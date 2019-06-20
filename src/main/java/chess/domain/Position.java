package chess.domain;

import java.util.Objects;

public class Position {
    private static final int DIAGONAL_DIVIDER = 2;

    private final Coordinate x;
    private final Coordinate y;

    public Position(final Coordinate x, final Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public Direction direction(Position position) {
        return Direction.valueOf(position.x.subtract(x), position.y.subtract(y));
    }

    public int distance(Position target, Direction direction) {
        if (direction.isDiagonal()) {
            return distanceDiagonal(target);
        }
        return distanceCross(target);
    }

    public int distance(Position target) {
        return Math.abs(x.subtract(target.x)) + Math.abs(y.subtract(target.y));
    }

    private int distanceDiagonal(Position target) {
        return (Math.abs(x.subtract(target.x)) + Math.abs(y.subtract(target.y))) / DIAGONAL_DIVIDER;
    }

    private int distanceCross(Position target) {
        return Math.abs(x.subtract(target.x) + y.subtract(target.y));
    }

    public Position add(Direction operand) {
        return new Position(new Coordinate(x.add(operand.getX())), new Coordinate(y.add(operand.getY())));
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
