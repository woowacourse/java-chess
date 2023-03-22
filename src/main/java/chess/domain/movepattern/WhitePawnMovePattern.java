package chess.domain.movepattern;

public enum WhitePawnMovePattern implements MovePattern {

    UP(0, 1),
    LEFT_TOP(-1, 1),
    RIGHT_TOP(1, 1);

    private final int fileVector;
    private final int rankVector;

    WhitePawnMovePattern(final int fileVector, final int rankVector) {
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