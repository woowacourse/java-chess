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

    private final int fileGap;
    private final int rankGap;

    Direction(final int fileGap, final int rankGap) {
        this.fileGap = fileGap;
        this.rankGap = rankGap;
    }

    static Direction of(final int fileDistance, final int rankDistance) {
        return Arrays.stream(values())
                .filter(it -> it.fileGap == fileDistance)
                .filter(it -> it.rankGap == rankDistance)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    int fileGap() {
        return fileGap;
    }

    int rankGap() {
        return rankGap;
    }
}
