package chess.domain;

import java.util.*;

public class Column {
    private static final Map<String, Column> columns = new HashMap<>();

    static {
        List<String> columnElements = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        for (String columnElement : columnElements) {
            columns.put(columnElement, new Column(columnElement));
        }
    }

    private String column;

    public Column(String column) {
        this.column = column;
    }

    public static Column valueOf(String column) {
        return columns.get(column);
    }

    public String moveColumn(int distance) {
        int newColumnAscii = column.charAt(0) + distance;

        return String.valueOf((char) newColumnAscii);
    }

    public static List<Column> getColumns() {
        return new ArrayList<>(columns.values());
    }

    public String getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return column;
    }

    public int getDifference(Column anotherColumn) {
        return column.charAt(0) - anotherColumn.toString().charAt(0);
    }
}
