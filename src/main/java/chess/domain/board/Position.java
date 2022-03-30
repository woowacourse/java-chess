package chess.domain.board;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position implements Comparable<Position> {

    private static final HashMap<String, Position> CACHE = new HashMap<>(64);
    private static final int NO_SIZE = 0;
    private static final int SIZE_START_INDEX = 0;

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position from(String rawPosition) {
        final Column column = Column.from(rawPosition.substring(0, 1));
        final Row row = Row.from(rawPosition.substring(1));
        return Position.of(column, row);
    }

    public static Position of(Column column, Row row) {
        return CACHE.computeIfAbsent(column.name() + row.name(), ignored -> new Position(column, row));
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
        return new Position(this.column.flip(), this.row);
    }

    public Position flipVertically() {
        return new Position(this.column, this.row.flip());
    }

    public Position flipDiagonally() {
        return new Position(this.column.flip(), this.row.flip());
    }

    public List<Position> pathTo(Position otherPosition) {
        List<Row> rowPath = this.row.pathTo(otherPosition.row);
        List<Column> columnPath = this.column.pathTo(otherPosition.column);
        if (rowPath.size() == NO_SIZE) {
            return getVerticalPositions(columnPath);
        }
        if (columnPath.size() == NO_SIZE) {
            return getHorizontalPositions(rowPath);
        }
        return getDiagonalPositions(rowPath, columnPath);
    }

    private List<Position> getVerticalPositions(final List<Column> columnPath) {
        return columnPath.stream()
            .map(column -> new Position(column, this.row))
            .collect(Collectors.toList());
    }

    private List<Position> getHorizontalPositions(final List<Row> rowPath) {
        return rowPath.stream()
            .map(row -> new Position(this.column, row))
            .collect(Collectors.toList());
    }

    private List<Position> getDiagonalPositions(final List<Row> rowPath, final List<Column> columnPath) {
        return IntStream.range(SIZE_START_INDEX, rowPath.size())
            .mapToObj(index -> new Position(columnPath.get(index), rowPath.get(index)))
            .collect(Collectors.toList());
    }

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
    }

    @Override
    public int compareTo(Position o) {
        return Comparator.comparing(Position::getRow, Comparator.reverseOrder()).thenComparing(Position::getColumn)
            .compare(this, o);
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
}

