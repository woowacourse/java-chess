package chess.domain.position;

import java.util.Arrays;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    private final int rankGap;
    private final int fileGap;

    Direction(int rankGap, int fileGap) {
        this.rankGap = rankGap;
        this.fileGap = fileGap;
    }

    public static Direction of(int rankDistance, int fileDistance) {
        return Arrays.stream(values())
                .filter(it -> it.rankGap == rankDistance)
                .filter(it -> it.fileGap == fileDistance)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    public int fileGap() {
        return fileGap;
    }

    public int rankGap() {
        return rankGap;
    }
}
