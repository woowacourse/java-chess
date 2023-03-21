package chess.domain.movepattern;

public enum RookMovePattern implements MovePattern {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private final int fileVector;
    private final int rankVector;

    RookMovePattern(final int fileVector, final int rankVector) {
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
