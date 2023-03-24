package chess.domain.movingStrategy;

public final class MoveDownTwoLeft extends KnightMovingStrategy {
    private final static MoveDownTwoLeft INSTANCE = new MoveDownTwoLeft(-1, -2);

    private MoveDownTwoLeft(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }


    public static MoveDownTwoLeft instance() {
        return INSTANCE;
    }
}
