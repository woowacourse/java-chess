package chess.domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Position {

    private static final int AROUND_DISTANCE = 1;

    private final Column column;
    private final Row row;

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(final String value) {
        validateValue(value);
        final var column = Column.of(value.substring(0, 1));
        final var row = Row.of(value.substring(1));

        return new Position(column, row);
    }

    private static void validateValue(final String value) {
        if (value.length() != 2) {
            throw new IllegalArgumentException("[ERROR] 위치는 열과 행으로 이루어져야 합니다.");
        }
    }

    public static Position valueOf(final Column column, final Row row) {
        return new Position(column, row);
    }

    public boolean isStraight(final Position target) {
        return isVertical(target) || isHorizontal(target);
    }

    public boolean isVertical(final Position target) {
        return column.isSame(target.column);
    }

    public boolean isHorizontal(final Position target) {
        return row.isSame(target.row);
    }

    public boolean isDiagonal(final Position target) {
        int columnGap = column.gap(target.column);
        int rowGap = row.gap(target.row);
        return columnGap == rowGap;
    }

    public int columnGap(final Position target) {
        return column.gap(target.column);
    }

    public int rowGap(final Position target) {
        return row.gap(target.row);
    }

    public boolean isAbove(final Position target) {
        return row.isGreaterThan(target.row);
    }

    public boolean isBelow(final Position target) {
        return row.isSmallerThan(target.row);
    }

    public boolean isSameRow(final Row row) {
        return this.row.isSame(row);
    }

    public List<Position> calculatePath(final Position target, final Direction direction) {
        if (rowGap(target) <= AROUND_DISTANCE && columnGap(target) <= AROUND_DISTANCE) {
            return Collections.emptyList();
        }
        return calculateByDirection(target, direction);
    }

    private List<Position> calculateByDirection(final Position target, final Direction direction) {
        if (direction.isVertical()) {
            return verticalPaths(target);
        }
        if (direction.isHorizontal()) {
            return horizontalPaths(target);
        }
        if (direction.isDiagonal()) {
            return diagonalPaths(target);
        }
        return Collections.emptyList();
    }

    private List<Position> verticalPaths(final Position target) {
        final List<Position> positions = new ArrayList<>();
        for (Row row : row.rowPaths(target.row)) {
            positions.add(Position.valueOf(this.column, row));
        }
        return positions;
    }

    private List<Position> horizontalPaths(final Position target) {
        final List<Position> positions = new ArrayList<>();
        for (Column column : column.columnPaths(target.column)) {
            positions.add(Position.valueOf(column, this.row));
        }
        return positions;
    }

    private List<Position> diagonalPaths(final Position target) {
        final List<Position> positions = new ArrayList<>();
        final List<Column> columns = column.columnPaths(target.column);
        final List<Row> rows = row.rowPaths(target.row);
        for (int i = 0; i < columns.size(); i++) {
            positions.add(Position.valueOf(columns.get(i), rows.get(i)));
        }
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
