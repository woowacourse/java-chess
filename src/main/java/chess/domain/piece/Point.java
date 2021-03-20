package chess.domain.piece;

import java.util.Objects;

public final class Point {

    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 8;
    private static final Point[] POINTS = new Point[MAX_INDEX];

    static {
        for (int i = 0; i < MAX_INDEX; i++) {
            POINTS[i] = new Point(i);
        }
    }

    private final int point;

    private Point(final int point) {
        this.point = point;
    }

    public static Point from(final int point) {
        validate(point);

        return POINTS[point];
    }

    private static void validate(final int position) {
        if (position < MIN_INDEX || position >= MAX_INDEX) {
            throw new IllegalArgumentException("인덱스는 0이상 7이하이어야 합니다.");
        }
    }

    public Point add(final int distance) {
        return from(point + distance);
    }

    public boolean isAdd(final int point) {
        return this.point + point >= MIN_INDEX && this.point + point < MAX_INDEX;
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

    public int point() {
        return point;
    }
}
