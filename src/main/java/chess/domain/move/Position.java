package chess.domain.move;

import chess.domain.board.Coordinate;

import java.util.Objects;

public class Position {
    private static final char BOARD_UP_END = '8';
    private static final char BOARD_DOWN_END = '1';
    private static final char BOARD_LEFT_END = 'a';
    private static final char BOARD_RIGHT_END = 'h';

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
        boolean xInField = (x >= BOARD_DOWN_END) && (x <= BOARD_UP_END);
        boolean yInField = (y >= BOARD_LEFT_END) && (x <= BOARD_RIGHT_END);

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
