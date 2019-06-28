package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {
    private static final Map<String, Position> positions = new HashMap<>();

    static {
        List<Column> columns = Column.getColumns();

        for (Column column : columns) {
            joinRowAndPut(column);
        }
    }

    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    private static void joinRowAndPut(Column column) {
        List<Row> rows = Row.getRows();

        for (Row row : rows) {
            positions.put(column.toString() + row.toString(), new Position(row, column));
        }
    }

    public static Position valueOf(String position) {
        checkValidPosition(position);
        return positions.get(position);
    }

    private static void checkValidPosition(String position) {
        if (positions.get(position) == null) {
            throw new IllegalArgumentException(position + "유효하지 않은 위치입니다.");
        }
    }

    public static List<Position> getRowPositions(String rowName) {
        return positions.values()
                .stream()
                .filter(v -> v.row == Row.valueOf(rowName))
                .collect(Collectors.toList());
    }

    public Position movePosition(int rowDistance, int columnDistance) {
        String movedRow = row.moveRow(rowDistance);
        String movedColumn = column.moveColumn(columnDistance);

        return positions.get(movedColumn + movedRow);
    }

    public int getRowDifference(Position anotherPosition) {
        return this.row.getDifference(anotherPosition.row);
    }


    public int getColumnDifference(Position anotherPosition) {
        return this.column.getDifference(anotherPosition.column);
    }

    @Override
    public String toString() {
        return column.toString() + row.toString();
    }
}
