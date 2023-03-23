package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<Integer, Rank> RANK_CACHE = new HashMap<>();

    static {
        for (Rank rank : values()) {
            RANK_CACHE.put(rank.index, rank);
        }
    }

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    private static Rank indexOf(int index) {
        Rank rank = RANK_CACHE.get(index);
        if (rank == null) {
            throw new UnsupportedOperationException();
        }
        return rank;
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
        return indexOf(this.index + 1);
    }

    private Rank down() {
        return indexOf(this.index - 1);
    }

    public int minus(Rank other) {
        return index - other.index;
    }

    public int getIndex() {
        return index;
    }

}
