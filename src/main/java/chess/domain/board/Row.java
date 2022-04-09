package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ;

    private static final Pattern ROW_PATTERN = Pattern.compile("[1-8]");
    private static final String INVALID_ROW_EXCEPTION = "존재하지 않는 행입니다.";
    private static final int MAX_ROW_VALUE = 8;

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row from(String rawRow) {
        if (!ROW_PATTERN.matcher(rawRow).matches()) {
            throw new IllegalArgumentException(INVALID_ROW_EXCEPTION);
        }
        return from(Integer.parseInt(rawRow));
    }

    private static Row from(int value) {
        return Arrays.stream(values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ROW_EXCEPTION));
    }

    public Row flip() {
        return Arrays.stream(Row.values())
                .filter(row -> row.value == (MAX_ROW_VALUE - this.value + 1))
                .findFirst()
                .orElseThrow();
    }

    public int directedDistance(Row otherRow) {
        return this.value - otherRow.value;
    }

    public int distance(Row otherRow) {
        return Math.abs(directedDistance(otherRow));
    }

    public List<Row> pathTo(Row otherRow) {
        if (this.value < otherRow.value) {
            return this.rightPathTo(otherRow);
        }
        return this.leftPathTo(otherRow);
    }

    private List<Row> rightPathTo(Row otherRow) {
        List<Row> path = new ArrayList<>();
        for (int i = this.value + 1; i < otherRow.value; i++) {
            path.add(Row.from(i));
        }
        return path;
    }

    private List<Row> leftPathTo(Row otherRow) {
        List<Row> path = new ArrayList<>();
        for (int i = this.value - 1; i > otherRow.value; i--) {
            path.add(Row.from(i));
        }
        return path;
    }

    public int getValue() {
        return value;
    }
}
