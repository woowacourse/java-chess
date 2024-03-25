package chess.domain.board.position;

import java.util.Arrays;

public enum Row {
    ONE(7),
    TWO(6),
    THREE(5),
    FOUR(4),
    FIVE(3),
    SIX(2),
    SEVEN(1),
    EIGHT(0);

    private final int index;

    Row(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Row calculateNextRow(int distance) {
        return Arrays.stream(values())
                .filter(row -> row.index == this.index + distance)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Column 이 없습니다."));
    }

    public boolean isNextInRange(int distance) {
        int nextIndex = index + distance;
        return EIGHT.index <= nextIndex && nextIndex <= ONE.index;
    }
}
