package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {

    private static final Map<Integer, Point> POINT_CACHE = new HashMap<>();
    private static final int DECIMAL = 10;
    private static final int VERTICAL_POSITION_START = 1;
    private static final int VERTICAL_POSITION_END = 2;
    private static final int HORIZONTAL_POSITION = 0;

    private final LineNumber horizontal;
    private final LineNumber vertical;

    private Point(LineNumber horizontal, LineNumber vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static Point of(int horizontal, int vertical) {
        LineNumber horizontalNumber = LineNumber.of(horizontal);
        LineNumber verticalNumber = LineNumber.of(vertical);
        return POINT_CACHE.computeIfAbsent(toKey(horizontal, vertical), ignored -> new Point(horizontalNumber, verticalNumber));
    }

    private static Integer toKey(int horizontal, int vertical) {
        return horizontal * DECIMAL + vertical;
    }

    public static Point of(String argument) {
        return Point.of(argument.charAt(HORIZONTAL_POSITION) - 'a' + 1,
                Integer.parseInt(argument.substring(VERTICAL_POSITION_START, VERTICAL_POSITION_END)));
    }

    public int subtractHorizontal(Point other) {
        return horizontal.subtract(other.horizontal);
    }

    public int subtractVertical(Point other) {
        return vertical.subtract(other.vertical);
    }

    public int moveHorizontal(int moveDistance) {
        return horizontal.moveLine(moveDistance);
    }

    public int moveVertical(int moveDistance) {
        return vertical.moveLine(moveDistance);
    }

    public boolean isInRangeNext(int dx, int dy) {
        return horizontal.isInRangeNext(dx) && vertical.isInRangeNext(dy);
    }

    public String convertPointToId() {
        return horizontal.changeHorizontalId() + vertical.changeVerticalId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(horizontal, point.horizontal) && Objects.equals(vertical, point.vertical);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    @Override
    public String toString() {
        return "Point{" +
                "horizontal=" + horizontal +
                ", vertical=" + vertical +
                '}';
    }
}
