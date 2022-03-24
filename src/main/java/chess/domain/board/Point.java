package chess.domain.board;

import chess.domain.piece.move.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {

    private static final Map<Integer, Point> POINT_CACHE = new HashMap<>();
    public static final int DECIMAL = 10;

    private final LineNumber horizontal;
    private final LineNumber vertical;

    private Point(LineNumber horizontal, LineNumber vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static Point of(int horizontal, int vertical) {
        LineNumber horizontalNumber = LineNumber.of(horizontal);
        LineNumber verticalNumber = LineNumber.of(vertical);
        return POINT_CACHE.computeIfAbsent(keys(horizontal, vertical), ignored -> new Point(horizontalNumber, verticalNumber));
    }

    private static Integer keys(int horizontal, int vertical) {
        return horizontal * DECIMAL + vertical;
    }

    public static Point of(String argument) {
        return Point.of(argument.charAt(0) - 'a',
                Integer.parseInt(argument.substring(1, 2)));
    }

    public int subtractHorizontal(Point other) {
        return horizontal.subtract(other.horizontal);
    }

    public int subtractVertical(Point other) {
        return vertical.subtract(other.vertical);
    }

    public Point next(Direction direction) {
        return Point.of(horizontal.next(direction.getDx()),
                vertical.next(direction.getDy()));
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
