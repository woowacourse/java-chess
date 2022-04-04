package chess.domain.position;

import java.util.*;
import java.util.stream.Collectors;

public final class Position {

    private static final int AROUND_DISTANCE = 1;
    private static final int POSITION_SIZE = 2;
    private static final String ERROR_POSITION_VALUE_FORMAT = "[ERROR] 위치는 열과 행, 두 문자의 조합으로 쓰세요.";

    private final Column column;
    private final Row row;
    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        generateAllPositions();
    }

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(final String value) {
        validateValue(value);
        return CACHE.get(value.toUpperCase());
    }

    public static Position valueOf(final Column column, final Row row) {
        return CACHE.get(column.name() + row.getValue());
    }

    private static void validateValue(final String value) {
        if (value.length() != POSITION_SIZE) {
            throw new IllegalArgumentException(ERROR_POSITION_VALUE_FORMAT);
        }
        validateColumnAndRowValue(value);
    }

    private static void validateColumnAndRowValue(String value) {
        Column.of(value.substring(0, 1));
        Row.of(value.substring(1, 2));
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
        return columnGap(target) == rowGap(target);
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

    public List<Position> positionsInPathToTarget(final Position target, final Direction direction) {
        if (isAdjoining(target)) {
            return Collections.emptyList();
        }
        return findAllPositionsInPath(target, direction);
    }

    private boolean isAdjoining(Position target) {
        return rowGap(target) <= AROUND_DISTANCE && columnGap(target) <= AROUND_DISTANCE;
    }

    private List<Position> findAllPositionsInPath(final Position target, final Direction direction) {
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
        return row.rowPaths(target.row).stream()
                .map(row -> Position.valueOf(this.column, row))
                .collect(Collectors.toList());
    }

    private List<Position> horizontalPaths(final Position target) {
        return column.columnPaths(target.column).stream()
                .map(column -> Position.valueOf(column, this.row))
                .collect(Collectors.toList());
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

    private static void generateAllPositions() {
        Arrays.stream(Column.values())
                .forEach(column -> generateOneColumn(column));
    }

    private static void generateOneColumn(Column column) {
        Arrays.stream(Row.values())
                .forEach(row -> CACHE.put(column.name() + row.getValue(), new Position(column, row)));
    }

    @Override
    public String toString() {
        return column.name() + row.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
