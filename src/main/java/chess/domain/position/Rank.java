package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int order;

    Rank(final int order) {
        this.order = order;
    }

    public Rank move(final int step) {
        return Arrays.stream(Rank.values())
                .filter(it -> it.order == this.order + step)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public int subtractOrder(final Rank other) {
        return this.order - other.order;
    }
}
