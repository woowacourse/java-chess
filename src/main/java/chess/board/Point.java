package chess.board;

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
}
