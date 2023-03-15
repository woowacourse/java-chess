package techcourse.fp.movingStrategy;

public class RightTwoDownStrategy extends KnightMovingStrategy {

    private RightTwoDownStrategy(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static RightTwoDownStrategy create() {
        return new RightTwoDownStrategy(2, -1);
    }
}
