package chess.domain.piece;

import java.util.Objects;

public class Position {
    private static final char MIN_X_RANGE = 'a';
    private static final char MAX_X_RANGE = 'h';
    private static final char MIN_Y_RANGE = '1';
    private static final char MAX_Y_RANGE = '8';

    private final char x;
    private final char y;

    public Position(char x, char y) {
        validate(x, y);
        this.x = x;
        this.y = y;
    }

    public Position next(int xTravel, int yTravel) {
        return new Position((char) (x + xTravel), (char) (y + yTravel));
    }

    private void validate(char x, char y) {
        if (x < MIN_X_RANGE || x > MAX_X_RANGE
                || y < MIN_Y_RANGE || y > MAX_Y_RANGE) {
            throw new IllegalArgumentException("체스 말의 위치가 Grid 범위를 벗어났습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
