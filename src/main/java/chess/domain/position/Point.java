package chess.domain.position;

import java.util.Objects;

import chess.BoardSize;

public class Point {

    private static final String ERROR_OUT_OF_RANGE = "인덱스는 0이상 7이하이어야 합니다.";

    private final int point;

    private Point(int point) {
        this.point = point;
    }

    public static Point from(int point) {
        if (isOutOfBounds(point)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }

        return new Point(point);
    }

    private static boolean isOutOfBounds(int point) {
        return point < BoardSize.BOUND.getMinIndex() || point > BoardSize.BOUND.getMaxIndex();
    }

    public boolean isInRange() {
        return !isOutOfBounds(point);
    }

    public Point add(int distance) {
        return new Point(point + distance);
    }

    public int getPoint() {
        return point;
    }

    public boolean equalsTo(int point) {
        return this.point == point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point1 = (Point) o;
        return point == point1.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
