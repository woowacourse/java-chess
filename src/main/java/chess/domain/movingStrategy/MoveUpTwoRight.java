package chess.domain.movingStrategy;

public final class MoveUpTwoRight extends KnightMovingStrategy {

    private MoveUpTwoRight(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveUpTwoRight create() {
        return new MoveUpTwoRight(1, 2);
    }
}
