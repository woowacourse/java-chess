package domain.position;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private static final Map<Integer, Position> cache = new LinkedHashMap<>();

    static {
        List<Integer> rows = List.of(8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);

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
        return new ArrayList<>(cache.values());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(column, position.column) && Objects.equals(row, position.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
