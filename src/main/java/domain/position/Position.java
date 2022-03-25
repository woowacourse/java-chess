package domain.position;

import java.util.Objects;

public final class Position {

    private final XPosition x;
    private final YPosition y;

    private Position(final XPosition x, final YPosition y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(final XPosition xPosition, final YPosition yPosition) {
        return new Position(xPosition, yPosition);
    }

    public static Position of(final String x, final String y) {
        return Position.of(XPosition.of(x), YPosition.of(y));
    }

    public int getX() {
        return x.getX();
    }

    public int getY() {
        return y.getY();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return y == position.y && x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return "{" + x.getX() + ", " + y.getY() + "}";
    }
}
