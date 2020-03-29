package chess.domain.move;

import java.util.Objects;

public class Position {
    private static final char BOARD_UP_END = '8';
    private static final char BOARD_DOWN_END = '1';
    private static final char BOARD_LEFT_END = 'a';
    private static final char BOARD_RIGHT_END = 'h';
    private static final char X_ASCII_BASE = '0';
    private static final char Y_ASCII_BASE = ('a' - 1);

    private final Coordinate x;
    private final Coordinate y;

    private Position(Coordinate x, Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(Coordinate x, Coordinate y) {
        return new Position(x, y);
    }

    public static Position of(String position) {
        validate(position);
        char[] chars = position.toCharArray();
        char y = chars[0];
        char x = chars[1];
        Coordinate coordinateX = Coordinate.of(x - X_ASCII_BASE);
        Coordinate coordinateY = Coordinate.of(y - Y_ASCII_BASE);

        return of(coordinateX, coordinateY);
    }

    private static void validate(String position) {
        char[] chars = position.toCharArray();
        char y = chars[0];
        char x = chars[1];
        boolean xInField = (x >= BOARD_DOWN_END) && (x <= BOARD_UP_END);
        boolean yInField = (y >= BOARD_LEFT_END) && (y <= BOARD_RIGHT_END);

        if (!xInField || !yInField) {
            throw new IllegalArgumentException("보드의 크기를 벗어났습니다.");
        }
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
