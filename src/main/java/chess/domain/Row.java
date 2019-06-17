package chess.domain;

import java.util.*;

public class Row {
    private static final int MIN_ROW_ELEMENT = 1;
    private static final int MAX_ROW_ELEMENT = 8;

    private static final Map<String, Row> rows = new HashMap<>();

    private final String row;

    static {
        for (int i = MIN_ROW_ELEMENT; i <= MAX_ROW_ELEMENT; i++) {
            String parsedRow = String.valueOf(i);
            rows.put(parsedRow, new Row(parsedRow));
        }
    }

    public Row(String row) {
        this.row = row;
    }

    public static Row valueOf(String row) {
        return rows.get(row);
    }

    public static List<Row> getRows() {
        return new ArrayList<>(rows.values());
    }

    @Override
    public String toString() {
        return row;
    }
}
