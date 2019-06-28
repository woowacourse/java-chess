package model;

import java.util.Objects;

public class Position {
    final static int START_OF_NUMBERING = 1;

    private Coordinate x;
    private Coordinate y;

    private Position(Coordinate x, Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return new Position(Coordinate.of(x), Coordinate.of(y));
    }

    public static Position of(String position) {
        int xCoordinate = position.substring(0, 1).toLowerCase().charAt(0) - 'a';
        int yCoordinate = Integer.parseInt(position.substring(1)) - 1;
        return Position.of(xCoordinate, yCoordinate);
    }

    public Position of(Direction direction) {
        return Position.of(x.getValue() + direction.getDx(), y.getValue() + direction.getDy());
    }

    public boolean isValid() {
        return x.isValid() && y.isValid();
    }

    public Coordinate getX() {
        return x;
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

    @Override
    public String toString() {
        String xCoordinate = String.valueOf((char) ('a' + x.getValue()));
        String yCoordinate = String.valueOf(y.getValue() + START_OF_NUMBERING);
        return xCoordinate + yCoordinate;
    }
}
