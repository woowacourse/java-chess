package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public enum Row {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    ;

    private static final Pattern ROW_PATTERN = Pattern.compile("[1-8]");

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row from(String rawRow) {
        if (!ROW_PATTERN.matcher(rawRow).matches()) {
            throw new IllegalArgumentException("존재하지 않는 행입니다.");
        }
        return from(Integer.parseInt(rawRow) - 1);
    }

    private static Row from(int value) {
        return Arrays.stream(values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행입니다."));
    }

    public Row flip() {
        return Arrays.stream(Row.values())
                .filter(it -> it.value == (7 - this.value))
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
        if (this.value < otherRow.value){
            return this.rightPathTo(otherRow);
        }
        return this.leftPathTo(otherRow);
    }

    private List<Row> rightPathTo(Row otherRow) {
        List<Row> path = new ArrayList<>();
        for (int i = this.value + 1; i <  otherRow.value; i++) {
            path.add(Row.from(i));
        }
        return path;
    }

    private List<Row> leftPathTo(Row otherRow) {
        List<Row> path = new ArrayList<>();
        for (int i = this.value - 1; i >  otherRow.value; i--) {
            path.add(Row.from(i));
        }
        return path;
    }
}
