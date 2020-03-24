package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {

    private static final Map<String, Point> points;

    static {
        points = new HashMap<>();
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                String name = nameOf(column, row);
                points.put(name, new Point(column, row));
            }
        }
    }

    private final Column column;
    private final Row row;
    private Point(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    private static String nameOf(Column column, Row row) {
        return column.getName() + row.getName();
    }

    public static Point from(String name) {
        Point point = points.get(name.toUpperCase());
        validate(name, point);
        return point;
    }

    private static void validate(String name, Point point) {
        if (Objects.isNull(point)) {
            throw new IllegalArgumentException(String.format("%s는 잘못된 입력입니다.", name));
        }
    }
}
