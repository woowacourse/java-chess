package techcourse.fp.chess.movingStrategy;

public class UpTwoRightStrategy extends KnightMovingStrategy {

    private UpTwoRightStrategy(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static UpTwoRightStrategy create() {
        return new UpTwoRightStrategy(1, 2);
    }
}
