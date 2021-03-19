package chess.domain.board;

import chess.domain.piece.MoveVector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Point {

    private static final Map<String, Point> POINT_POOL = new HashMap<>();

    static {
        for (Row row : Row.values()) {
            generatePointPoolRow(row);
        }
    }

    private final Column x;
    private final Row y;

    public Point(Column x, Row y) {
        this.x = x;
        this.y = y;
    }

    private static void generatePointPoolRow(Row row) {
        for (Column column : Column.values()) {
            POINT_POOL.put(
                column.coordinate() + row.coordinate(), new Point(column, row));
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

    public static List<Point> AllPoints() {
        return new ArrayList<>(POINT_POOL.values());
    }

    public Point yAxisOppositePoint() {
        return Point.of(this.x.coordinate() + this.y.oppositeRow().coordinate());
    }

    public Point oppositePoint() {
        return Point.of(this.x.oppositeColumn().coordinate() + this.y.oppositeRow().coordinate());
    }

    public boolean isRow(Row row) {
        return y == row;
    }

    public boolean isColumn(Column column) {
        return x == column;
    }

    public int XDifference(Point source) {
        return this.x.index() - source.x.index();
    }

    public int YDifference(Point source) {
        return this.y.index() - source.y.index();
    }

    public Point movedPoint(MoveVector moveVector) {
        return Point.of(movedPointName(moveVector));
    }

    private String movedPointName(MoveVector moveVector) {
        return Column.foundColumnByIndex(movedXIndex(moveVector)).coordinate() +
            Row.foundRowByIndex(movedYIndex(moveVector)).coordinate();
    }

    private int movedYIndex(MoveVector moveVector) {
        return y.index() + moveVector.getVertical();
    }

    private int movedXIndex(MoveVector moveVector) {
        return x.index() + moveVector.getHorizon();
    }
}
