package chess.domain.board;

import chess.domain.piece.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Point {

    private static final Map<String, Point> POINT_POOL = new HashMap<>();

    private final Column x;
    private final Row y;

    static {
        for (Row row : Row.values()) {
            iterateXCoordinate(row);
        }
    }

    public Point(Column x, Row y) {
        this.x = x;
        this.y = y;
    }

    private static void iterateXCoordinate(Row row) {
        for (Column column : Column.values()) {
            POINT_POOL.put(
                column.xCoordinate() + row.yCoordinate(), new Point(column, row));
        }
    }

    public static Point of(String point) {
        validatePoint(point);
        return POINT_POOL.get(point);
    }

    private static void validatePoint(String point) {
        if (!POINT_POOL.containsKey(point)) {
            throw new IllegalArgumentException("존재하지 않는 좌표입니다.");
        }
    }

    public static List<Point> getAllPoints() {
        return new ArrayList<>(POINT_POOL.values());
    }

    public Point yAxisOpposite() {
        return Point.of(this.x.xCoordinate() + this.y.opposite().yCoordinate());
    }

    public Point opposite() {
        return Point.of(this.x.opposite().xCoordinate() + this.y.opposite().yCoordinate());
    }

    public boolean isRow(Row row) {
        return y == row;
    }

    public boolean isColumn(Column column) {
        return x == column;
    }

    public int minusX(Point source) {
        return this.x.index() - source.x.index();
    }

    public int minusY(Point source) {
        return this.y.index() - source.y.index();
    }

    public Point move(Vector vector) {
        return Point.of(nextPoint(vector));
    }

    private String nextPoint(Vector vector) {
        return Column.columnByIndex(newX(vector)).xCoordinate() +
            Row.rowByIndex(newY(vector)).yCoordinate();
    }

    private int newY(Vector vector) {
        return y.index() + vector.getY();
    }

    private int newX(Vector vector) {
        return x.index() + vector.getX();
    }
}
