package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank findValueOf(String rankInput) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.getValue() == Integer.parseInt(rankInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 rank값 입니다."));
    }

    public int getValue() {
        return value;
    }
}
