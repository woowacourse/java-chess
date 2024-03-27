package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank from(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("랭크는 1에서 8 사이의 숫자이어야 합니다."));
    }

    public int calculateDistance(Rank rank) {
        return Math.abs(subtract(rank));
    }

    public int subtract(Rank rank) {
        return value - rank.value;
    }

    public int getValue() {
        return value;
    }
}
