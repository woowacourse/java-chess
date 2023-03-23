package chess.domain.movingStrategy;

public final class MoveDownTwoRight extends KnightMovingStrategy {

    private final static MoveDownTwoRight INSTANCE = new MoveDownTwoRight(1, -2);

    private MoveDownTwoRight(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveDownTwoRight get() {
        return INSTANCE;
    }
}
