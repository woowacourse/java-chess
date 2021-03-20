package chess.domain.position;

import java.util.Objects;

public final class Position {
    private static final char MIN_X_RANGE = 'a';
    private static final char MAX_X_RANGE = 'h';
    private static final char MIN_Y_RANGE = '1';
    private static final char MAX_Y_RANGE = '8';

    private final char x;
    private final char y;

    public Position(final String position) {
        this(position.charAt(0), position.charAt(1));
    }

    public Position(final char x, final char y) {
        this.x = x;
        this.y = y;
    }

    public final char x() {
        return x;
    }

    public final char y() {
        return y;
    }

    public final boolean isInValidRange() {
        return x < MIN_X_RANGE || x > MAX_X_RANGE || y < MIN_Y_RANGE || y > MAX_Y_RANGE;
    }

    public final Position next(final int xDegree, final int yDegree) {
        return new Position((char) (x + xDegree), (char) (y + yDegree));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }
}
