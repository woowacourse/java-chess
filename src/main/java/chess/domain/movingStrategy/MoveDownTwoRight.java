package chess.domain.movingStrategy;

public final class MoveDownTwoRight extends KnightMovingStrategy {

    private static final MoveDownTwoRight INSTANCE = new MoveDownTwoRight(1, -2);

    private MoveDownTwoRight(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveDownTwoRight instance() {
        return INSTANCE;
    }
}
