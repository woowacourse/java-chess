package chess.domain.board;

import java.util.Arrays;

public enum Row {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row from(int value) {
        return Arrays.stream(values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
    }

    public static Row from(String row) {
        try {
            return from(Integer.parseInt(row));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("행은 숫자여야 합니다.");
        }
    }

    public int calculateDifference(Row target) {
        return this.value - target.value;
    }

    public Row move(int rowDifference) {
        return from(value + rowDifference);
    }

    public int getValue() {
        return value;
    }
}
