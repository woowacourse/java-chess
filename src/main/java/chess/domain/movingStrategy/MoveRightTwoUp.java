package chess.domain.movingStrategy;

public final class MoveRightTwoUp extends KnightMovingStrategy {

    private MoveRightTwoUp(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveRightTwoUp create() {
        return new MoveRightTwoUp(2, 1);
    }
}
