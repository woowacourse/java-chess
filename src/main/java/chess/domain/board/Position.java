package chess.domain.board;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position implements Comparable<Position> {
    private static final int DISTANCE_NOT_MOVED = 0;
    private static final Map<String, Position> cash = new HashMap<>();

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
        cash.put(this.toString(), this);
    }

    public static Position of(Column column, Row row) {
        String key = "" + column + row;
        if (cash.containsKey(key)) {
            return cash.get(key);
        }
        return new Position(column, row);
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

    Position flipHorizontally() {
        return Position.of(this.column.flip(), this.row);
    }

    Position flipVertically() {
        return Position.of(this.column, this.row.flip());
    }

    Position flipDiagonally() {
        return Position.of(this.column.flip(), this.row.flip());
    }

    List<Position> pathTo(Position otherPosition) {
        List<Row> rowPath = this.row.pathTo(otherPosition.row);
        List<Column> columnPath = this.column.pathTo(otherPosition.column);

        if (rowPath.size() == DISTANCE_NOT_MOVED) {
            return this.pathInColumn(columnPath);
        }
        if (columnPath.size() == DISTANCE_NOT_MOVED) {
            return pathInRow(rowPath);
        }
        return pathInDiagonal(rowPath, columnPath);
    }

    private List<Position> pathInColumn(List<Column> columnPath) {
        return columnPath.stream()
                .map(column -> Position.of(column, this.row))
                .collect(Collectors.toList());
    }

    private List<Position> pathInRow(List<Row> rowPath) {
        return rowPath.stream()
                .map(row -> Position.of(this.column, row))
                .collect(Collectors.toList());
    }

    private List<Position> pathInDiagonal(List<Row> rowPath, List<Column> columnPath) {
        return IntStream.range(0, rowPath.size())
                .mapToObj(index -> Position.of(columnPath.get(index), rowPath.get(index)))
                .collect(Collectors.toList());
    }

    Column getColumn() {
        return column;
    }

    Row getRow() {
        return row;
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
}

