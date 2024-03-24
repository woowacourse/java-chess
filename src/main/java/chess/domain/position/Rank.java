package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7);

    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 7;
    private static final int TO_NORTH = 1;
    private static final int TO_SOUTH = -1;

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    public Rank toNorth() {
        if (index >= MAX_INDEX) {
            throw new IllegalStateException("북쪽으로 이동할 수 없습니다.");
        }

        return findRank(index + TO_NORTH);
    }

    public Rank toSouth() {
        if (index <= MIN_INDEX) {
            throw new IllegalStateException("남쪽으로 이동할 수 없습니다.");
        }

        return findRank(index + TO_SOUTH);
    }

    private Rank findRank(int southToNorth) {
        return Arrays.stream(Rank.values())
                .filter(row -> row.index == southToNorth)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 가로 위치가 없습니다."));
    }

    public int calculateDifference(Rank rank) {
        return rank.index - this.index;
    }
}
