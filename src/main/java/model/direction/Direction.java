package model.direction;

import model.Position;

public enum Direction {
    N(0, 1),
    S(0, -1),
    E(1, 0),
    W(-1, 0),
    NE(1, 1),
    NW(-1, 1),
    SE(1, -1),
    SW(-1, -1);

    private final int fileDifferential;
    private final int rankDifferential;

    Direction(int fileDifferential, int rankDifferential) {
        this.fileDifferential = fileDifferential;
        this.rankDifferential = rankDifferential;
    }

    public boolean isMovedPositionAvailable(Position position) {
        return position.isAvailablePosition(position.file() + fileDifferential, position.rank() + rankDifferential);
    }
}
