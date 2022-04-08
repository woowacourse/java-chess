package chess.domain.position;

import java.util.*;

public final class Position {

    private static final int AROUND_DISTANCE = 1;
    private static final int COLUMN_ROW_LENGTH = 2;
    private static final Map<String, Position> caches = new HashMap<>();

    private final Integer id;
    private final Column column;
    private final Row row;
    private final Integer boardId;

    public Position(final Integer id, final Column column, final Row row, final Integer boardId) {
        this.id = id;
        this.column = column;
        this.row = row;
        this.boardId = boardId;
    }

    public Position(final Column column, final Row row) {
        this(null, column, row, null);
    }

    public Position(final Column column, final Row row, final int boardId) {
        this(null, column, row, boardId);
    }

    public static Position of(final String value) {
        validateValue(value);
        caches.computeIfAbsent(value, it -> new Position(Column.of(value.substring(0, 1)), Row.of(value.substring(1))));
        return caches.get(value);
    }

    private static void validateValue(final String value) {
        if (value.length() != COLUMN_ROW_LENGTH) {
            throw new IllegalArgumentException("위치는 열과 행으로 이루어져야 합니다.");
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

    public Column getColumn() {
        return column;
    }

    public Row getRow() {
        return row;
    }

    public int getId() {
        return id;
    }

    public int getBoardId() {
        return boardId;
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
        for (Row pathsRow : row.rowPaths(target.row)) {
            positions.add(Position.valueOf(this.column, pathsRow));
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

    @Override
    public String toString() {
        return row.value() + column.name();
    }
}
