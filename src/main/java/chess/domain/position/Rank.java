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

    private final int southToNorth;

    Rank(int southToNorth) {
        this.southToNorth = southToNorth;
    }

    public Rank toNorth() {
        if (southToNorth >= 8) {
            throw new IllegalStateException("북쪽으로 이동할 수 없습니다.");
        }
        return find(southToNorth + 1);
    }

    public Rank toSouth() {
        if (southToNorth <= 1) {
            throw new IllegalStateException("남쪽으로 이동할 수 없습니다.");
        }
        return find(southToNorth - 1);
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
