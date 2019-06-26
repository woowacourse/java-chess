package chess.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {
    private static final int MIN_ROW_ELEMENT = 1;
    private static final int MAX_ROW_ELEMENT = 8;

    private static final Map<String, Row> rows = new HashMap<>();

    static {
        for (int i = MIN_ROW_ELEMENT; i <= MAX_ROW_ELEMENT; i++) {
            String parsedRow = String.valueOf(i);
            rows.put(parsedRow, new Row(parsedRow));
        }
    }

    private final String row;

    public Row(String row) {
        this.row = row;
    }

    public static Row valueOf(String row) {
        return rows.get(row);
    }

    public String moveRow(int distance) {
        int newRowAscii = row.charAt(0) + distance;

        return String.valueOf((char) newRowAscii);
    }

    public static List<Row> getRows() {
        return new ArrayList<>(rows.values());
    }

    public String getRow() {
        return row;
    }

    @Override
    public String toString() {
        return row;
    }

    public int getDifference(Row anotherRow) {
        return row.charAt(0) - anotherRow.toString().charAt(0);
    }
}
