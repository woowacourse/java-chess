package chess.model;

import java.util.Objects;

public class Point {
    private final Position xPosition;
    private final Position yPosition;

    public Point(final Position xPosition, final Position yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Point(int xPosition, int yPosition) {
        this.xPosition = Position.valueOf(xPosition);
        this.yPosition = Position.valueOf(yPosition);
    }

    public int calculateXsDiff(final Point target) {
        return this.xPosition.calculateDiff(target.xPosition);
    }

    public int calculateYsDiff(final Point target) {
        return this.yPosition.calculateDiff(target.yPosition);
    }

    public boolean isSameY(final int yPosition) {
        return this.yPosition.equals(Position.valueOf(yPosition));
    }

    public int calculateYsSub(final Point target) {
        return yPosition.calculateSub(target.yPosition);
    }

    public int calculateXsSub(final Point target) {
        return xPosition.calculateSub(target.xPosition);
    }

    public Point moveOneStep(final Direction direction) {
        try {
            return new Point(xPosition.add(direction.getDeltaX()), yPosition.add(direction.getDeltaY()));
        } catch (Exception e) {
            return null;
        }
    }

    public int getX() {
        return xPosition.getValue();
    }

    public int getY() {
        return yPosition.getValue();
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
