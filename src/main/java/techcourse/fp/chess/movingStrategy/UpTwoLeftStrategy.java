package techcourse.fp.chess.movingStrategy;

public final class UpTwoLeftStrategy extends KnightMovingStrategy {

    private UpTwoLeftStrategy(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static UpTwoLeftStrategy create() {
        return new UpTwoLeftStrategy(-1, 2);
    }
}
