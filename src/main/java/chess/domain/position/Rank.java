package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

    static Rank from(int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    int gapWith(Rank rank) {
        return this.value - rank.value;
    }

    public int value() {
        return value;
    }

    public boolean isSame(final int rank) {
        return this.value == rank;
    }
}
