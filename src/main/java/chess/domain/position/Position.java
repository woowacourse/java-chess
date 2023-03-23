package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Position {

    public static final int POSITION_STRING_LENGTH = 2;

    private static final Map<Integer, Position> cache = new HashMap<>();

    static {
        List<Integer> rows = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> columns = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        rows.stream()
                .flatMap(row -> columns.stream()
                        .map(column -> new Position(row, column)))
                .forEach(position -> cache.put(position.hashCode(), position));
    }

    private final int row;
    private final int column;

    private Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(int row, int column) {
        return cache.get(Objects.hash(row, column));
    }

    public static Position of(String input) {
        if (input.length() != POSITION_STRING_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 좌표값입니다.");
        }
        int row = RowToNumber.of(input.charAt(1));
        int column = ColumnToNumber.of(input.charAt(0));
        return cache.get(Objects.hash(row, column));
    }

    public static List<Position> getAllPosition() {
        List<Integer> rows = List.of(8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        return rows.stream()
                .flatMap(row -> columns.stream()
                        .map(column -> Position.of(row, column)))
                .collect(Collectors.toList());
    }

    public static List<Position> getRouteOf(Position start, Position end) {
        if (Direction.of(start, end) == Direction.OTHER) {
            return new ArrayList<>(List.of(start, end));
        }
        return calculateRoute(start, end);
    }

    private static List<Position> calculateRoute(Position start, Position end) {
        int moveCount = calculateMoveCount(start, end);
        int rowIncrement = start.calculateRowGap(end) / moveCount;
        int columnIncrement = start.calculateColumnGap(end) / moveCount;

        Position previousPosition = start;
        List<Position> route = new ArrayList<>(List.of(start));
        for (int i = 0; i < moveCount; i++) {
            Position nextPosition = previousPosition.add(rowIncrement, columnIncrement);
            route.add(nextPosition);
            previousPosition = nextPosition;
        }
        return route;
    }

    private static int calculateMoveCount(Position start, Position end) {
        int rowGap = start.calculateRowGap(end);
        int columnGap = start.calculateColumnGap(end);
        return Math.max(Math.abs(rowGap), Math.abs(columnGap));
    }

    public int calculateRowGap(Position other) {
        return other.row - this.row;
    }

    public int calculateColumnGap(Position other) {
        return other.column - this.column;
    }

    private Position add(int rowIncrement, int columnIncrement) {
        return Position.of(row + rowIncrement, column + columnIncrement);
    }

    public Position moveUp() {
        return Position.of(row + 1, column);
    }

    public Position moveDown() {
        return Position.of(row - 1, column);
    }

    public Position moveUpLeft() {
        return Position.of(row + 1, column - 1);
    }

    public Position moveUpRight() {
        return Position.of(row + 1, column + 1);
    }

    public Position moveDownLeft() {
        return Position.of(row - 1, column - 1);
    }

    public Position moveDownRight() {
        return Position.of(row - 1, column + 1);
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
