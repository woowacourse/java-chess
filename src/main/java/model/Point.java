package model;

import java.util.Objects;

public class Point {

    private static final String POINT_INPUT_EXCEPTION_MESSAGE = "point 입력이 잘못되었습니다.";
    private final XPosition xPosition;
    private final YPosition yPosition;

    private Point(final XPosition xPosition, final YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public static Point valueOf(final String point) {
        if (point.length() != 2) {
            throw new IllegalArgumentException(POINT_INPUT_EXCEPTION_MESSAGE);
        }

        XPosition xPosition = XPosition.valueOf(point.charAt(0));
        YPosition yPosition = YPosition.valueOf(Integer.parseInt(point.substring(1, 2)));

        return new Point(xPosition, yPosition);
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
