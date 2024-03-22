package model.direction;

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

    public int fileDifferential() {
        return fileDifferential;
    }

    public int rankDifferential() {
        return rankDifferential;
    }
}
