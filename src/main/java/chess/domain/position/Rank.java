package chess.domain.position;

import java.util.Arrays;
import java.util.Optional;

import chess.domain.move.Direction;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    private static Optional<Rank> indexOf(int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findFirst();
    }

    public Rank move(Direction direction) {
        if (direction == Direction.UP) {
            return up();
        }
        if (direction == Direction.DOWN) {
            return down();
        }
        return this;
    }

    private Rank up() {
        return indexOf(this.index + 1)
                .orElseThrow(UnsupportedOperationException::new);
    }

    private Rank down() {
        return indexOf(this.index - 1)
                .orElseThrow(UnsupportedOperationException::new);
    }

    public int minus(Rank other) {
        return index - other.index;
    }

    public int getIndex() {
        return index;
    }

}
