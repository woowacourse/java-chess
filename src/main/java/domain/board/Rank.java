package domain.board;

import java.util.Arrays;

public enum Rank {
    ONE('1', 0),
    TWO('2', 1),
    THREE('3', 2),
    FOUR('4', 3),
    FIVE('5', 4),
    SIX('6', 5),
    SEVEN('7', 6),
    EIGHT('8', 7);

    private final Character value;

    private final int index;

    Rank(Character value, int index) {
        this.value = value;
        this.index = index;
    }

    public static Rank find(int rankCoordinate) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.index == rankCoordinate)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }

    public static Rank find(char value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value == value)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 좌표입니다."));
    }

    public int getIndex() {
        return index;
    }
}
