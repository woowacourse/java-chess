package chess.domain.movepattern;

public enum BlackPawnMovePattern implements MovePattern {

    DOWN(0, -1),
    LEFT_BOTTOM(-1, -1),
    RIGHT_BOTTOM(1, -1);

    private final int fileVector;
    private final int rankVector;

    BlackPawnMovePattern(final int fileVector, final int rankVector) {
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