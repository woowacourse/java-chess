package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position implements Comparable<Position> {

    private static final String INVALID_POSITION_EXCEPTION = "유효하지 않은 위치입니다.";

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        return Key.keys.stream()
                .filter(position -> position.column == column && position.row == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_POSITION_EXCEPTION));
    }

    public static Position from(String rawPosition) {
        String rawColumn = rawPosition.substring(0, 1);
        String rawRow = rawPosition.substring(1);
        return of(Column.from(rawColumn), Row.from(rawRow));
    }

    public int columnDistance(Position otherPosition) {
        return this.column.distance(otherPosition.column);
    }

    public int rowDistance(Position otherPosition) {
        return this.row.distance(otherPosition.row);
    }

    public int rowDirectedDistance(Position otherPosition) {
        return this.row.directedDistance(otherPosition.row);
    }

    public Position flipHorizontally() {
        return new Position(column.flip(), row);
    }

    public Position flipVertically() {
        return new Position(column, row.flip());
    }

    public Position flipDiagonally() {
        return new Position(column.flip(), row.flip());
    }

    public boolean existObstacleToOtherPosition(Position otherPosition, Predicate<Position> positionPredicate) {
        return pathTo(otherPosition).stream()
                .allMatch(positionPredicate);
    }

    private List<Position> pathTo(Position otherPosition) {
        List<Row> rowPath = this.row.pathTo(otherPosition.row);
        List<Column> columnPath = this.column.pathTo(otherPosition.column);

        if (rowPath.size() == 0) {
            return findHorizontalPath(columnPath);
        }
        if (columnPath.size() == 0) {
            return findVerticalPath(rowPath);
        }
        return findDiagonalPath(rowPath, columnPath);
    }

    private List<Position> findHorizontalPath(List<Column> columnPath) {
        return columnPath.stream()
                .map(column -> Position.of(column, row))
                .collect(Collectors.toList());
    }

    private List<Position> findVerticalPath(List<Row> rowPath) {
        return rowPath.stream()
                .map(row -> Position.of(column, row))
                .collect(Collectors.toList());
    }

    private List<Position> findDiagonalPath(List<Row> rowPath, List<Column> columnPath) {
        return IntStream.range(0, rowPath.size())
                .mapToObj(index -> Position.of(columnPath.get(index), rowPath.get(index)))
                .collect(Collectors.toList());
    }

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
    }

    public String stringName() {
        return column.getName() + row.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position that = (Position) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

    @Override
    public int compareTo(Position o) {
        return Comparator.comparing(Position::getRow, Comparator.reverseOrder())
                .thenComparing(Position::getColumn)
                .compare(this, o);
    }

    static class Key {
        private static final List<Position> keys;

        static {
            keys = Arrays.stream(Column.values())
                    .flatMap(column -> Arrays.stream(Row.values())
                            .map(row -> new Position(column, row))
                    ).collect(Collectors.toList());
        }
    }
}

