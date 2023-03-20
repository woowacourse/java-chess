package chess.domain.movepattern;

public enum KnightMovePattern implements MovePattern {

    UP_LEFT(-1, 2),
    LEFT_UP(-2, 1),
    LEFT_DOWN(-2, -1),
    DOWN_LEFT(-1, -2),
    DOWN_RIGHT(1, -2),
    RIGHT_DOWN(2, -1),
    RIGHT_UP(2, 1),
    UP_RIGHT(1, 2);

    private final int fileVector;
    private final int rankVector;

    KnightMovePattern(final int fileVector, final int rankVector) {
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
