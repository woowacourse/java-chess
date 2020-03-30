package chess.domain.position;

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

    private final int index;

    Row(int index) {
        this.index = index;
    }

    public static int size() {
        return values().length;
    }

    public Row plus(int number) {
        return Row.of(this.index + number);
    }

    public Row minus(int number) {
        return Row.of(this.index - number);
    }

    private static Row of(int number) {
        return Arrays.stream(Row.values())
                .filter(file -> file.index == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank의 범위를 초과하였습니다."));
    }

    public int getRowNumber() {
        return index;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}
