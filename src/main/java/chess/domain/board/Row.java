package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Row {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private static final int ROW_MAX = 7;
    private static final List<Row> REVERSE_ROWS = Arrays.asList(Row.values());

    static {
        REVERSE_ROWS.sort((row1, row2) -> row2.index() - row1.index());
    }

    private final String coordinate;

    Row(String coordinate) {
        this.coordinate = coordinate;
    }

    public static Row rowByIndex(int index) {
        return Arrays.stream(Row.values())
            .filter(row -> row.index() == index)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static List<Row> reversedRows() {
        return Collections.unmodifiableList(REVERSE_ROWS);
    }

    public Row opposingRow() {
        return rowByIndex(ROW_MAX - index());
    }

    public int index() {
        return ordinal();
    }

    public String coordinate() {
        return coordinate;
    }
}
