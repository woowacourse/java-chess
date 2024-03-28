package model.direction;

import java.util.Arrays;

public enum Direction {
    N(0, 1),
    S(0, -1),
    E(1, 0),
    W(-1, 0),
    NE(1, 1),
    NW(-1, 1),
    SE(1, -1),
    SW(-1, -1),
    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private final int fileDifferential;
    private final int rankDifferential;

    Direction(int fileDifferential, int rankDifferential) {
        this.fileDifferential = fileDifferential;
        this.rankDifferential = rankDifferential;
    }

    public Direction toOppositeDirection() {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.rankDifferential * -1 == rankDifferential
                        && direction.fileDifferential * -1 == fileDifferential)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("짝이 존재하지 않는 Direction입니다."));
    }

    public int fileDifferential() {
        return fileDifferential;
    }

    public int rankDifferential() {
        return rankDifferential;
    }
}
