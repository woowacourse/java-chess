package techcourse.fp.chess.movingStrategy;

public final class LeftTwoDownStrategy extends KnightMovingStrategy {

    private LeftTwoDownStrategy(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static LeftTwoDownStrategy create() {
        return new LeftTwoDownStrategy(-2, -1);
    }
}
