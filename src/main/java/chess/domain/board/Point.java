package chess.domain.board;

import chess.domain.piece.MoveVector;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Point {

    private static final Map<String, Point> POINT_POOL;

    static {
        POINT_POOL = Arrays.stream(Row.values())
            .flatMap(Point::pointPoolByRow)
            .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }

    private final Column column;
    private final Row row;

    public Point(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    private static Stream<SimpleEntry<String, Point>> pointPoolByRow(Row row) {
        return Arrays.stream(Column.values())
            .map(column -> new SimpleEntry<>(column.coordinate() + row.coordinate(),
                new Point(column, row)));
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

    public static List<Point> allPoints() {
        return new ArrayList<>(POINT_POOL.values());
    }

    public Point yAxisSymmetricPoint() {
        return Point.of(this.column.coordinate() + this.row.opposingRow().coordinate());
    }

    public Point originSymmetricPoint() {
        return Point
            .of(this.column.opposingColumn().coordinate() + this.row.opposingRow().coordinate());
    }

    public int columnDifference(Point source) {
        return this.column.index() - source.column.index();
    }

    public int rowDifference(Point source) {
        return this.row.index() - source.row.index();
    }

    public Point movedPoint(MoveVector moveVector) {
        return Point.of(movedPointCoordinate(moveVector));
    }

    private String movedPointCoordinate(MoveVector moveVector) {
        return Column.columnByIndex(movedXIndex(moveVector)).coordinate() +
            Row.rowByIndex(movedYIndex(moveVector)).coordinate();
    }

    private int movedYIndex(MoveVector moveVector) {
        return row.index() + moveVector.vertical();
    }

    private int movedXIndex(MoveVector moveVector) {
        return column.index() + moveVector.horizontal();
    }

    public String xCoordinate() {
        return column.coordinate();
    }

    public String yCoordinate() {
        return row.coordinate();
    }

    public boolean isLocatedIn(Row row) {
        return this.row == row;
    }

    public boolean isLocatedIn(Column column) {
        return this.column == column;
    }
}
