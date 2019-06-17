package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private static final List<String> positions = new ArrayList<>();

    private final Row row;
    private final Column column;

    static {
        List<Row> rows = Row.getRows();
        List<Column> columns = Column.getColumns();

        for (Column column : columns) {
            for (Row row : rows) {
                positions.add(column.toString() + row.toString());
            }
        }
    }

    public Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static boolean isValidPostion(String position) {
        return positions.contains(position);
    }

    public static List<String> getPositions() {
        return positions;
    }
}
