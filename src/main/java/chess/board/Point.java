package chess.board;

import chess.piece.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Point {

    private static final Map<String, Point> POINT_POOL = new HashMap<>();

    static {
        for (Row row : Row.values()) {
            iterateXCoordinate(row);
        }
    }

    private final Column x;
    private final Row y;

    public Point(Column x, Row y) {
        this.x = x;
        this.y = y;
    }

    private static void iterateXCoordinate(Row row) {
        for (Column column : Column.values()) {
            POINT_POOL.put(
                column.getXCoordinate() + row.getYCoordinate(), new Point(column, row));
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
        return Point.of(this.x.getXCoordinate() + this.y.opposite().getYCoordinate());
    }

    public Point opposite() {
        return Point.of(this.x.opposite().getXCoordinate() + this.y.opposite().getYCoordinate());
    }

    public boolean isRow(Row row) {
        return y == row;
    }

    public int minusX(Point source) {
        return this.x.getXIndex() - source.x.getXIndex();
    }

    public int minusY(Point source) {
        return this.y.getYIndex() - source.y.getYIndex();
    }

    public Point move(Vector vector) {
        return Point.of(newerName(vector));
    }

    private String newerName(Vector vector) {
        return Column.getByIndex(newerXIndex(vector)).getXCoordinate() +
            Row.getByIndex(newerYIndex(vector)).getYCoordinate();
    }

    private int newerYIndex(Vector vector) {
        return y.getYIndex() + vector.getVertical();
    }

    private int newerXIndex(Vector vector) {
        return x.getXIndex() + vector.getHorizon();
    }


}
