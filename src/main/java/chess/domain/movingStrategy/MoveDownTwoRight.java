package chess.domain.movingStrategy;

public final class MoveDownTwoRight extends KnightMovingStrategy {

    private MoveDownTwoRight(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveDownTwoRight create() {
        return new MoveDownTwoRight(1, -2);
    }
}
