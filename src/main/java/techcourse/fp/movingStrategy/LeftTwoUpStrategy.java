package techcourse.fp.movingStrategy;

public class LeftTwoUpStrategy extends KnightMovingStrategy {

    private LeftTwoUpStrategy(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static LeftTwoUpStrategy create() {
        return new LeftTwoUpStrategy(-2, 1);
    }
}
