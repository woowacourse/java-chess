package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

    private final String yCoordinate;
    private final int index;

    static {
        REVERSE_ROWS.sort(Comparator.comparingInt(Row::index).reversed());
    }

    Row(String yCoordinate, int index) {
        this.yCoordinate = yCoordinate;
        this.index = index;
    }

    public static Row rowByIndex(int index) {
        return Arrays.stream(Row.values())
            .filter(row -> row.index == index)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static List<Row> reverseRows() {
        return Collections.unmodifiableList(REVERSE_ROWS);
    }

    public String yCoordinate() {
        return yCoordinate;
    }

    public int index() {
        return index;
    }

    public Row opposite() {
        return rowByIndex(ROW_MAX - index);
    }
}
