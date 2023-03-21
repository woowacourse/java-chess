package chess.domain.movingStrategy;

public final class MoveLeftTwoUp extends KnightMovingStrategy {

    private MoveLeftTwoUp(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveLeftTwoUp create() {
        return new MoveLeftTwoUp(-2, 1);
    }
}
