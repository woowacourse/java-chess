package chess.domain.movingStrategy;

public final class MoveLeftTwoUp extends KnightMovingStrategy {
    private final static MoveLeftTwoUp INSTANCE = new MoveLeftTwoUp(-2, 1);

    private MoveLeftTwoUp(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveLeftTwoUp instance() {
        return INSTANCE;
    }
}
