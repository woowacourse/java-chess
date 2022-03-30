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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return xPosition == position.xPosition && yPosition == position.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }

    @Override
    public String toString() {
        return "{" + xPosition.getXPosition() + ", " + yPosition.getYPosition() + "}";
    }
}
