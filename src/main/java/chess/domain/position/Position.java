package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Position {

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
        List<Position> route = new ArrayList<>();
        route.add(start);
        route.addAll(start.calculateRoute(start.calculateRowGap(end), start.calculateColumnGap(end)));
        return route;
    }

    public int calculateRowGap(Position other) {
        return other.row - this.row;
    }

    public int calculateColumnGap(Position other) {
        return other.column - this.column;
    }

    private List<Position> calculateRoute(int rowGap, int columnGap) {
        List<Position> route = new ArrayList<>();
        int unit = Math.max(Math.abs(rowGap), Math.abs(columnGap));
        int rowCoefficient = rowGap / unit;
        int columnCoefficient = columnGap / unit;
        for (int i = 1; i <= unit; i++) {
            route.add(Position.of(row + rowCoefficient * i, column + columnCoefficient * i));
        }
        return route;
    }

    public Position moveUp() {
        return Position.of(row + 1, column);
    }

    public Position moveDown() {
        return Position.of(row - 1, column);
    }

    public Position moveLeft() {
        return Position.of(row, column - 1);
    }

    public Position moveRight() {
        return Position.of(row, column + 1);
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
