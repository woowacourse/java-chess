package chess.domain.board;

import java.util.Objects;

public class Position {
    private final Coordinate x;
    private final Coordinate y;

    private Position(Coordinate x, Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return new Position(new Coordinate(x), new Coordinate(y));
    }

    public static Position of(String position) {
        validate(position);
        char[] chars = position.toCharArray();
        char y = chars[0];
        char x = chars[1];

        return of(x - '0', (y + 1) - 'a');
    }

    private static void validate(String position) {
        char[] chars = position.toCharArray();
        char y = chars[0];
        char x = chars[1];
        boolean xInField = (x >= '1') && (x <= '8');
        boolean yInField = (y >= 'a') && (x <= 'h');

        if (xInField && yInField) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x.getCoordinate() +
                ", y=" + y.getCoordinate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x.getCoordinate();
    }

    public int getY() {
        return y.getCoordinate();
    }
}
