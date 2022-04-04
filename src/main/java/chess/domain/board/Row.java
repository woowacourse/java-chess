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

    private static final int ROW_FLIP_CRITERIA = 9;
    private static final String INVALID_ROW_MESSAGE = "존재하지 않는 행입니다.";

    private final int number;

    Row(int number) {
        this.number = number;
    }

    public static Row from(char number) {
        return from(Character.getNumericValue(number));
    }

    private static Row from(int value) {
        return Arrays.stream(values())
            .filter(row -> row.number == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_ROW_MESSAGE));
    }

    public Row flip() {
        return Arrays.stream(Row.values())
            .filter(it -> it.number == (ROW_FLIP_CRITERIA - number))
            .findFirst()
            .orElseThrow();
    }

    public int directedDistance(Row otherRow) {
        return otherRow.number - number;
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

    public Row nextWith(final UnitDirectVector direction) {
        return Row.from(direction.nextRowNumber(number));
    }

    public boolean isNextValid(final UnitDirectVector direction) {
        final int nextRowNumber = direction.nextRowNumber(number);
        return ONE.number <= nextRowNumber && nextRowNumber <= EIGHT.number;
    }
}
