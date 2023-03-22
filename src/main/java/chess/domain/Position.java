package chess.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Position {
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

    public int calculateRowDistance(Position position) {
        return this.row.distance(position.row);
    }

    public int calculateColumnDistance(Position position) {
        return this.column.distance(position.column);
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
        if (isSameColumn(position) || isSameRow(position)) {
            return true;
        }
        return column.distance(position.column) != row.distance(position.row);
    }

    public boolean isSameRow(Position position) {
        return this.row == position.row;
    }

    public boolean isNotSameRow(Position position) {
        return this.row != position.row;
    }

    public boolean isSameColumn(Position position) {
        return this.column == position.column;
    }

    public boolean isSameColumn(Column column) {
        return this.column == column;
    }

    public boolean isNotSameColumn(Position position) {
        return this.column != position.column;
    }

    public boolean isNotConstantFunction(Position position) {
        return isNotSameRow(position) && isNotSameColumn(position);
    }

    public int getRowDirection(Position position) {
        return row.direction(position.row);
    }

    public int getColumnDirection(Position position) {
        return column.direction(position.column);
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
