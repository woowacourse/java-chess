package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Row {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);

    private static final int ROW_MAX = 7;
    private static final List<Row> REVERSE_ROWS = Arrays.asList(Row.values());

    static {
        REVERSE_ROWS.sort((row1, row2) -> row2.index() - row1.index());
    }

    private final String coordinate;
    private final int index;

    Row(String coordinate, int index) {
        this.coordinate = coordinate;
        this.index = index;
    }

    public static Row getByIndex(int index) {
        return Arrays.stream(Row.values())
            .filter(row -> row.index == index)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static List<Row> reverseRows() {
        return Collections.unmodifiableList(REVERSE_ROWS);
    }

    public String coordinate() {
        return coordinate;
    }

    public int index() {
        return index;
    }

    public Row opposite() {
        return getByIndex(ROW_MAX - index);
    }
}
