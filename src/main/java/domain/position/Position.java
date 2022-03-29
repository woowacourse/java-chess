package domain.position;

import java.util.Objects;

public final class Position {

    private final XPosition xPosition;
    private final YPosition yPosition;

    private Position(final XPosition xPosition, final YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public static Position of(final XPosition xPosition, final YPosition yPosition) {
        return new Position(xPosition, yPosition);
    }

    public static Position of(final String x, final String y) {
        return Position.of(XPosition.of(x), YPosition.of(y));
    }

    public int getXPosition() {
        return xPosition.getXPosition();
    }

    public int getYPosition() {
        return yPosition.getYPosition();
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
        return yPosition == position.yPosition && xPosition == position.xPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yPosition, xPosition);
    }

    @Override
    public String toString() {
        return "{" + xPosition.getXPosition() + ", " + yPosition.getYPosition() + "}";
    }
}
