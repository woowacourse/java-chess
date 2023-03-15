package techcourse.fp.chess.movingStrategy;

public class DownTwoRight extends KnightMovingStrategy {

    private DownTwoRight(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static DownTwoRight create() {
        return new DownTwoRight(1, -2);
    }
}
