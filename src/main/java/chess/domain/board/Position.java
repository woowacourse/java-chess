package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> points;

    static {
        points = new HashMap<>();
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                String name = nameOf(column, row);
                points.put(name, new Position(column, row));
            }
        }
    }

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    private static String nameOf(Column column, Row row) {
        return column.getName() + row.getName();
    }

    public static Position of(Column column, Row row) {
        return points.get(nameOf(column, row));
    }

    public static Position from(String name) {
        Position position = points.get(name.toUpperCase());
        validate(name, position);
        return position;
    }

    private static void validate(String name, Position position) {
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException(String.format("%s는 잘못된 입력입니다.", name));
        }
    }
}
