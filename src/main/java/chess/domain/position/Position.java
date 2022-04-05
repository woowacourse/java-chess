package chess.domain.position;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();

    static {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                CACHE.put(toName(column, row), new Position(column, row));
            }
        }
    }

    private final Column column;
    private final Row row;

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position from(final String name) {
        final Position position = CACHE.get(name);
        checkNull(position);
        return CACHE.get(name);
    }

    public static Position of(final Column column, final Row row) {
        return CACHE.get(toName(column, row));
    }

    private static String toName(final Column column, final Row row) {
        return column.getName() + row.getName();
    }

    private static void checkNull(final Position position) {
        if (position == null) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 위치입니다.");
        }
    }

    public Position move(final Direction direction) {
        return new Position(direction.moveFile(column), direction.moveRow(row));
    }

    public boolean isSameRow(final Row row) {
        return row == this.row;
    }

    public boolean isSameColumn(final Column column) {
        return column == this.column;
    }

    public int calculateColumnDistance(final Position from) {
        return this.column.calculateDistance(from.column);
    }

    public int calculateRowDistance(final Position from) {
        return this.row.calculateDistance(from.row);
    }

    public static List<Position> toPositions() {
        return new ArrayList<>(CACHE.values());
    }

    public String getName() {
        return toName(column, row);
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
