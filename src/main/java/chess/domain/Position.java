package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Position {
    private static final Map<String, Position> positions = new HashMap<>();

    private final Row row;
    private final Column column;

    static {
        List<Row> rows = Row.getRows();
        List<Column> columns = Column.getColumns();

        for (Column column : columns) {
            for (Row row : rows) {
                positions.put(column.toString() + row.toString(), new Position(row, column));
            }
        }
    }

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position valueOf(String position) {
        return positions.get(position);
    }

    public static List<Position> getRow(String rowName) {
        return positions.values()
                .stream()
                .filter(v -> v.row == Row.valueOf(rowName))
                .collect(Collectors.toList());
    }
}
