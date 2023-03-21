package chess.domain.movingStrategy;

public final class MoveLeftTwoDown extends KnightMovingStrategy {

    private MoveLeftTwoDown(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveLeftTwoDown create() {
        return new MoveLeftTwoDown(-2, -1);
    }
}
