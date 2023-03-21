package chess.domain.movepattern;

public enum PawnMovePattern implements MovePattern {

    UP(0, 1),
    LEFT_TOP(-1, 1),
    RIGHT_TOP(1, 1),
    DOWN(0, -1),
    LEFT_BOTTOM(-1, -1),
    RIGHT_BOTTOM(1, -1);

    private final int fileVector;
    private final int rankVector;

    PawnMovePattern(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    public boolean isDiagonalMove() {
        return this != UP && this != DOWN;
    }

    @Override
    public int nextFileIndex(final int currentFileIndex) {
        return currentFileIndex + this.fileVector;
    }

    @Override
    public int nextRankIndex(final int currentRankIndex) {
        return currentRankIndex + this.rankVector;
    }
}
