package chess.domain.movepattern;

public enum BishopMovePattern implements MovePattern {

    LEFT_TOP(-1, 1),
    RIGHT_TOP(1, 1),
    LEFT_BOTTOM(-1, -1),
    RIGHT_BOTTOM(1, -1);

    private final int fileVector;
    private final int rankVector;

    BishopMovePattern(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
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
