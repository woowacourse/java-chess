package chess.domain.movepattern;

public enum QueenMovePattern implements MovePattern {


    LEFT_TOP(-1, 1),
    RIGHT_TOP(1, 1),
    LEFT_BOTTOM(-1, -1),
    RIGHT_BOTTOM(1, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final int fileVector;
    private final int rankVector;

    QueenMovePattern(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    @Override
    public int fileVector() {
        return fileVector;
    }

    @Override
    public int rankVector() {
        return rankVector;
    }
}
