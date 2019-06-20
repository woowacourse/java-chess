package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {
    private static final Map<String, Position> positions = new HashMap<>();

    static {
        List<Row> rows = Row.getRows();
        List<Column> columns = Column.getColumns();

        for (Column column : columns) {
            for (Row row : rows) {
                positions.put(column.toString() + row.toString(), new Position(row, column));
            }
        }
    }

    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position valueOf(String position) {
        return positions.get(position);
    }

    public Position movePosition(int rowDistance, int columnDistance) {
        String movedRow = row.moveRow(rowDistance);
        String movedColumn = column.moveColumn(columnDistance);

        return positions.get(movedColumn + movedRow);
    }

    public static List<Position> getRowPositions(String rowName) {
        return positions.values()
                .stream()
                .filter(v -> v.row == Row.valueOf(rowName))
                .collect(Collectors.toList());
    }

    public int getRowDifference(Position anotherPosition) {
        return this.row.getDifference(anotherPosition.row);
    }


    public int getColumnDifference(Position anotherPosition) {
        return this.column.getDifference(anotherPosition.column);
    }
}
