package chess.domain.position;

import java.util.Arrays;

public enum Direction {

    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    NW(-1, 1);

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

    public int rankGap() {
        return rankGap;
    }

    public int fileGap() {
        return fileGap;
    }
}
