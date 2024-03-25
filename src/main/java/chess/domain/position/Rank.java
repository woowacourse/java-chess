package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    EIGHTH(8),
    SEVENTH(7),
    SIXTH(6),
    FIFTH(5),
    FOURTH(4),
    THIRD(3),
    SECOND(2),
    FIRST(1);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank from(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Rank는 1에서 8 사이의 숫자이어야 합니다."));
    }

    public boolean canMove(int diff) {
        return Arrays.stream(values())
                .anyMatch(rank -> rank.value == this.value + diff);
    }

    public Rank move(int diff) {
        return Rank.from(value + diff);
    }
}
