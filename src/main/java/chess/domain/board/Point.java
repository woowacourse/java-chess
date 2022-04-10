package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import chess.database.dto.PointDto;
import chess.domain.piece.move.Direction;

public class Point {

    private static final Map<Integer, Point> POINT_CACHE = new HashMap<>();
    private static final int LINE_NUMBER_COUNT = 2;
    private static final int DECIMAL = 10;

    private final LineNumber horizontal;
    private final LineNumber vertical;

    private Point(LineNumber horizontal, LineNumber vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static Point of(LineNumber horizontal, LineNumber vertical) {
        return POINT_CACHE.computeIfAbsent(toKey(horizontal, vertical),
            ignored -> new Point(horizontal, vertical));
    }

    private static Integer toKey(LineNumber horizontal, LineNumber vertical) {
        return horizontal.getNumber() * DECIMAL + vertical.getNumber();
    }

    public static Point of(int horizontal, int vertical) {
        LineNumber horizontalNumber = LineNumber.of(horizontal);
        LineNumber verticalNumber = LineNumber.of(vertical);
        return of(horizontalNumber, verticalNumber);
    }

    public static Point of(String argument) {
        validateArgumentSize(argument);
        LineNumber horizontalNumber = LineNumber.ofAlphabet(argument.charAt(0));
        LineNumber verticalNumber = LineNumber.of(argument.charAt(1));
        return of(horizontalNumber, verticalNumber);
    }

    private static void validateArgumentSize(String argument) {
        if (argument.length() != LINE_NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format("[ERROR] 좌표의 크기는 %d 여야 합니다.", DECIMAL));
        }
    }

    public static Point of(PointDto pointDto) {
        return of(pointDto.getHorizontal(), pointDto.getVertical());
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

    public boolean isInRangeNext(int dx, int dy) {
        return horizontal.isInRangeNext(dx) && vertical.isInRangeNext(dy);
    }

    public int getHorizontal() {
        return horizontal.getNumber();
    }

    public int getVertical() {
        return vertical.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point)o;
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
