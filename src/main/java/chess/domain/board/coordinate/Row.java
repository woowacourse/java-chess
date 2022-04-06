package chess.domain.board.coordinate;

import java.util.Arrays;

public enum Row {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public static Row of(String value) {
        return Arrays.stream(values())
                .filter(row -> Integer.toString(row.value).equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Row는 존재하지 않습니다."));
    }

    public Row move(int distance) {
        return Arrays.stream(values())
                .filter(row -> row.value == this.value + distance)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("조건에 맞는 Row가 존재하지 않습니다."));
    }

    public int getValue() {
        return value;
    }

    public int gap(Row row) {
        return row.value - value;
    }

    public boolean isSame(int row) {
        return this.value == row;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
