package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private static final int MAX_VALUE = 9;
    private static final String ERROR_NO_SUCH_ROW = "존재하지 않는 열입니다.";

    private final int value;

    Row(int value) {
        this.value = value;
    }

    private static Row from(int value) {
        return Arrays.stream(values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_SUCH_ROW));
    }

    Row goDown(int step) {
        return from(this.value - step);
    }

    Row flip() {
        return from(MAX_VALUE - this.value);
    }

    int directedDistance(Row otherRow) {
        return this.value - otherRow.value;
    }

    int distance(Row otherRow) {
        return Math.abs(directedDistance(otherRow));
    }

    List<Row> pathTo(Row otherRow) {
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

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
