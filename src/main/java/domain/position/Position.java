package domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Position {

    private static final Map<Integer, Position> cache = new HashMap<>();
    public static final List<Integer> PADDING_ROWS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    public static final List<Integer> PADDING_COLUMNS = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    public static final List<Integer> ORDERED_ROWS = List.of(8, 7, 6, 5, 4, 3, 2, 1);
    public static final List<Integer> ORDERED_COLUMNS = List.of(1, 2, 3, 4, 5, 6, 7, 8);
    public static final int COLUMN_INDEX = 0;
    public static final int ROW_INDEX = 1;

    static {
        for (int row : PADDING_ROWS) {
            setUpColumnsByRow(row);
        }
    }

    private final int row;
    private final int column;

    private Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String inputPoint) {
        int row = RowToNumber.of(inputPoint.charAt(ROW_INDEX));
        int column = ColumnToNumber.of(inputPoint.charAt(COLUMN_INDEX));

        return Position.of(row, column);
    }

    public static Position of(int row, int column) {
        return cache.get(Objects.hash(row, column));
    }

    private static void setUpColumnsByRow(int row) {
        for (int column : PADDING_COLUMNS) {
            Position position = new Position(row, column);
            cache.put(position.hashCode(), position);
        }
    }

    public static List<Position> getAllPosition() {
        return ORDERED_ROWS.stream()
                .flatMap(row -> ORDERED_COLUMNS.stream()
                        .map(column -> Position.of(row, column)))
                .collect(Collectors.toList());
    }

    public List<Position> getPathTo(Position end) {
        if (Direction.of(this, end) == Direction.OTHER) {
            return new ArrayList<>(List.of(end));
        }
        return calculatePath(calculateRowGap(end), calculateColumnGap(end));
    }

    public int calculateRowGap(Position other) {
        return other.row - this.row;
    }

    public int calculateColumnGap(Position other) {
        return other.column - this.column;
    }

    private List<Position> calculatePath(int rowGap, int columnGap) {
        List<Position> path = new ArrayList<>();
        int unit = Math.max(Math.abs(rowGap), Math.abs(columnGap));
        int rowCoefficient = rowGap / unit;
        int columnCoefficient = columnGap / unit;
        for (int i = 1; i <= unit; i++) {
            path.add(Position.of(row + rowCoefficient * i, column + columnCoefficient * i));
        }
        return path;
    }

    public boolean isBlackPawnInitialRow() {
        return this.row == 7;
    }

    public boolean isWhitePawnInitialRow() {
        return this.row == 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(column, position.column) && Objects.equals(row, position.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
