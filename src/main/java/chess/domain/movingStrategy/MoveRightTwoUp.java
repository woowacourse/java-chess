package chess.domain.movingStrategy;

public final class MoveRightTwoUp extends KnightMovingStrategy {

    private static final MoveRightTwoUp INSTANCE = new MoveRightTwoUp(2, 1);

    private MoveRightTwoUp(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveRightTwoUp instance() {
        return INSTANCE;
    }
}
