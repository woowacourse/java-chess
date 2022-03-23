package chess.domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position implements Comparable<Position> {

    private final static Map<String, Position> CACHE;

    private final Column column;
    private final Row row;

    static {
        CACHE = init().stream()
                .collect(Collectors.toMap(Position::createKey,
                        position -> position));
    }

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    private static List<Position> init() {
        List<Position> positions = new ArrayList<>();

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                positions.add(new Position(column, row));
            }
        }
        return positions;
    }

    public static Position valueOf(final String rawPosition) {
        if (!CACHE.containsKey(rawPosition)) {
            throw new IllegalArgumentException("[ERROR] 범위를 초과하였습니다.");
        }
        return CACHE.get(rawPosition);
    }

    public int subtractColumn(final Position position) {
        return this.column.subtract(position.column);
    }

    public int subtractRow(final Position position) {
        return this.row.subtract(position.row);
    }

    private String createKey() {
        return column.getName() + row.getValue();
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
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public int compareTo(Position position) {
        if (this.row.getValue() == position.row.getValue()) {
            return position.column.getValue() - this.column.getValue();
        }
        return position.row.getValue() - this.row.getValue();
    }
}
