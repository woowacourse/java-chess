package chess.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position {
    public static final Position IS_NULL = new Position(null, null);
    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final Row row, final Column column) {
        return new Position(row, column);
    }

    public static Position from(final String rowColumn) {
        final Row row = Row.from(String.valueOf(rowColumn.charAt(0)));
        final Column column = Column.from(String.valueOf(rowColumn.charAt(1)));

        return new Position(row, column);
    }

    public Position move(int row, int column) {
        return new Position(this.row.move(row), this.column.move(column));
    }

    public int calculateRowDistance(Row row) {
        return this.row.distance(row);
    }

    public int calculateColumnDistance(Column column) {
        return this.column.distance(column);
    }

    public List<Position> calculateBetweenPoints(Position end) {
        int rowDirection = end.row.direction(row);
        int columnDirection = end.column.direction(column);
        int maxDistance = Math.max(this.row.distance(end.row), this.column.distance(end.column));

        return IntStream.rangeClosed(1, maxDistance)
                .mapToObj(distance -> Position.of(
                        row.move(distance * rowDirection),
                        column.move(distance * columnDirection)
                ))
                .collect(Collectors.toList());
    }

    public boolean isNotLinearFunction(Position position) {
        final int slopeOfOne = 1;

        if (isSameColumn(position.getColumn()) || isSameRow(position.row)) {
            return true;
        }
        final int slope = column.distance(position.column) / row.distance(position.row);

        return slope != slopeOfOne;
    }

    public boolean isSameRow(Row row) {
        return this.row == row;
    }

    public boolean isNotSameRow(Row row) {
        return this.row != row;
    }

    public boolean isSameColumn(Column column) {
        return this.column == column;
    }

    public boolean isNotSameColumn(Column column) {
        return this.column != column;
    }

    public boolean isNotConstantFunction(Position position) {
        return isNotSameRow(position.getRow()) && isNotSameColumn(position.column);
    }

    public int getRowDirection(Position position) {
        return row.direction(position.row);
    }

    public int getColumnDirection(Position position) {
        return column.direction(position.column);
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
