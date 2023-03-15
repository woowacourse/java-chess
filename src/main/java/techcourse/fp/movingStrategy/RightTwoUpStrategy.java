package techcourse.fp.movingStrategy;

public class RightTwoUpStrategy extends KnightMovingStrategy {

    private RightTwoUpStrategy(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static RightTwoUpStrategy create() {
        return new RightTwoUpStrategy(2, 1);
    }
}
