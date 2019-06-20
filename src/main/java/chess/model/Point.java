package chess.model;

import java.util.Objects;

public class Point {

    private final XPosition xPosition;
    private final YPosition yPosition;

    public Point(int xPosition, int yPosition) {
        this.xPosition = XPosition.valueOf(xPosition);
        this.yPosition = YPosition.valueOf(yPosition);
    }

    public int calculateXsDiff(final Point target) {
        return this.xPosition.calculateXsDiff(target.xPosition);
    }

    public int calculateYsDiff(final Point target) {
        return this.yPosition.calculateYsDiff(target.yPosition);
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
