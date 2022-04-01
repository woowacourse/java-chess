package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private final int number;

    Row(int number) {
        this.number = number;
    }

    public static Row from(String rawRow) {
        return from(Integer.parseInt(rawRow) - 1);
    }

    private static Row from(int value) {
        return Arrays.stream(values())
            .filter(row -> row.number == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다."));
    }

    public Row flip() {
        return Arrays.stream(Row.values())
            .filter(it -> it.number == (7 - number))
            .findFirst()
            .orElseThrow();
    }

    public int directedDistance(Row otherRow) {
        return number - otherRow.number;
    }

    public int distance(Row otherRow) {
        return Math.abs(directedDistance(otherRow));
    }

    public List<Row> pathTo(Row otherRow) {
        if (number < otherRow.number) {
            return rightPathTo(otherRow);
        }
        return leftPathTo(otherRow);
    }

    private List<Row> rightPathTo(Row otherRow) {
        List<Row> path = new ArrayList<>();
        for (int i = number + 1; i < otherRow.number; i++) {
            path.add(Row.from(i));
        }
        return path;
    }

    private List<Row> leftPathTo(Row otherRow) {
        List<Row> path = new ArrayList<>();
        for (int i = number - 1; i > otherRow.number; i--) {
            path.add(Row.from(i));
        }
        return path;
    }
}
