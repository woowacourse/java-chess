package chess.model;

import java.util.Objects;

public class Point {

    private final XPosition xPosition;
    private final YPosition yPosition;

    public Point(XPosition xPosition, YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point point = (Point) o;
        return Objects.equals(xPosition, point.xPosition) &&
                Objects.equals(yPosition, point.yPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }
}
