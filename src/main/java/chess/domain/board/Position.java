package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Position {

    private static final String OUT_OF_BOUND_MESSAGE = "체스판 범위를 벗어난 위치입니다.";

    private static final Map<String, Position> cache = new HashMap<>();

    static {
        for (final Row row : Row.values()) {
            for (final Column column : Column.values()) {
                final String key = generateKey(row, column);
                final Position position = new Position(row, column);
                cache.put(key, position);
            }
        }
    }

    private final Row row;
    private final Column column;

    private Position(Row row, Column col) {
        this.row = row;
        this.column = col;
    }

    public static Position of(Row row, Column column) {
        String key = generateKey(row, column);
        return Optional.ofNullable(cache.get(key))
            .orElseThrow(() -> new IllegalArgumentException(OUT_OF_BOUND_MESSAGE));
    }

    public static Position of(int row, int column) {
        String key = generateKey(Row.findRowByIndex(row), Column.findColumnByIndex(column));
        return Optional.ofNullable(cache.get(key))
            .orElseThrow(() -> new IllegalArgumentException(OUT_OF_BOUND_MESSAGE));
    }

    public static Position of(String input) {
        String key = generateKey(Row.findRow(input.charAt(1)), Column.findColumn(input.charAt(0)));
        return Optional.ofNullable(cache.get(key))
            .orElseThrow(() -> new IllegalArgumentException(OUT_OF_BOUND_MESSAGE));
    }

    private static String generateKey(Row row, Column column) {
        return row.name() + column.name();
    }

    public Column getColumn() {
        return column;
    }

    public int getRowAsIndex() {
        return row.getIndex();
    }

    public int getColumnAsIndex() {
        return column.getIndex();
    }

    public Position nextPosition(int xDegree, int yDegree) {
        return Position.of(this.row.getIndex() + yDegree, this.column.getIndex() + xDegree);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
