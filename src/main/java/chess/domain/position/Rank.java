package chess.domain.position;

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

    private static final int MIN_SOUTH_TO_NORTH = 1;
    private static final int MAX_SOUTH_TO_NORTH = 8;
    private static final int TO_NORTH = 1;
    private static final int TO_SOUTH = -1;

    private final int southToNorth;

    Rank(int southToNorth) {
        this.southToNorth = southToNorth;
    }

    public Rank toNorth() {
        if (southToNorth >= MAX_SOUTH_TO_NORTH) {
            throw new IllegalStateException("북쪽으로 이동할 수 없습니다.");
        }

        return find(southToNorth + TO_NORTH);
    }

    public Rank toSouth() {
        if (southToNorth <= MIN_SOUTH_TO_NORTH) {
            throw new IllegalStateException("남쪽으로 이동할 수 없습니다.");
        }

        return find(southToNorth + TO_SOUTH);
    }

    private Rank find(int southToNorth) {
        return Arrays.stream(Rank.values())
                .filter(row -> row.southToNorth == southToNorth)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 가로 위치가 없습니다."));
    }

    public int calculateDifference(Rank rank) {
        return rank.southToNorth - this.southToNorth;
    }
}
