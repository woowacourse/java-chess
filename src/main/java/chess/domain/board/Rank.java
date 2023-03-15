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

    Rank(final int value) {
        this.value = value;
    }

    public static Rank from(final int value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public Rank plus() {
        int rankPlused = this.value + 1;
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == rankPlused)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    public Rank minus() {
        int rankMinused = this.value - 1;
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == rankMinused)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    public boolean isOver(final Rank rank) {
        return value > rank.value;
    }

    public int sub(final Rank rank) {
        return this.value - rank.value;
    }

    public int getValue() {
        return value;
    }
}
