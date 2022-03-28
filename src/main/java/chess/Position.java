package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private static final List<Position> cachedPositions;

    static {
        cachedPositions = Arrays.stream(Column.values())
                .flatMap(column -> Arrays.stream(Row.values())
                        .map(row -> new Position(column, row)))
                .collect(Collectors.toList());
    }

    private Column column;
    private Row row;

    private Position(final Column column, final Row row) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final String column, final int row) {
        return cachedPositions.stream()
                .filter(cachedPositions -> cachedPositions.column == Column.of(column) && cachedPositions.row == Row.of(row))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다."));
    }

    public static Position of(final Column column, final Row row) {
        return cachedPositions.stream()
                .filter(cachedPositions -> cachedPositions.column == column && cachedPositions.row == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다."));
    }

    public static Position of(final String position) {
        return cachedPositions.stream()
                .filter(cachedPositions -> isExist(position, cachedPositions))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다."));
    }

    private static boolean isExist(final String position, final Position cachedPositions) {
        return cachedPositions.column == Column.of(position.substring(0, 1)) &&
                cachedPositions.row == Row.of(position.substring(1, 2));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        final Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    public int getColumnDistance(final Position to) {
        return this.column.getDistance(to.column);
    }

    public int getRowDistance(final Position to) {
        return this.row.getDistance(to.row);
    }

    public boolean isPawnInitial() {
        return row.isPawnRow();
    }

    public boolean isSameColumn(final Position other) {
        return column == other.column;
    }

    public boolean canMoveToCurrentDirection(Direction direction, Position to) {
        return isValidPosition(this, to, direction);
    }

    private boolean isValidPosition(Position from, Position to, Direction direction) {
        final int columnVal = from.column.getValue() + direction.getColumn();
        final int rowVal = from.row.getValue() + direction.getRow();
        if (!from.canShift(columnVal, rowVal)) {
            return false;
        }
        Position nextPosition = otherShift(from, direction);
        if (nextPosition.equals(to)) {
            return true;
        }
        return isValidPosition(nextPosition, to, direction);
    }

    private Position otherShift(final Position position, final Direction direction) {
        final Column column = Column.of(position.column.getValue() + direction.getColumn());
        final Row row = Row.of(position.row.getValue() + direction.getRow());
        return Position.of(column, row);

    }

    private boolean canShift(final int column, final int row) {
        return column >= 1 && row >= 1 && column <= 8 && row <= 8;
    }

    public Position shift(final Direction direction) {
        final Column column = Column.of(this.column.getValue() + direction.getColumn());
        final Row row = Row.of(this.row.getValue() + direction.getRow());
        return Position.of(column, row);
    }

    public boolean equalsColumn(final int column) {
        return this.column.getValue() == column;
    }
}
