package chess.domain.position;

import java.util.Objects;

public class Position {
    private static final char MIN_X_RANGE = 'a';
    private static final char MAX_X_RANGE = 'h';
    private static final char MIN_Y_RANGE = '1';
    private static final char MAX_Y_RANGE = '8';

    private final char x;
    private final char y;

    public Position(final char x, final char y) {
        this.x = x;
        this.y = y;
    }

    public char getX() {
        return x;
    }

    public char getY() {
        return y;
    }

    public boolean isInGridRange() {
        return x >= MIN_X_RANGE && x <= MAX_X_RANGE && y >= MIN_Y_RANGE && y <= MAX_Y_RANGE;
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

    public Position stepOn(int xDegree, int yDegree) {
        return new Position((char) (x + xDegree), (char) (y + yDegree));
    }
}
