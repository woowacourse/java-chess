package model;

import java.util.Objects;

public class Position {
    private Coordinate x;
    private Coordinate y;

    private Position(Coordinate x, Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return new Position(Coordinate.of(x), Coordinate.of(y));
    }

    public Position of(Direction direction) {
        return Position.of(x.getValue() + direction.getDx(), y.getValue() + direction.getDy());
    }

    public boolean isValid() {
        return x.isValid() && y.isValid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
