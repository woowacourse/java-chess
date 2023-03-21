package chess.domain.movingStrategy;

public final class MoveRightTwoDown extends KnightMovingStrategy {

    private MoveRightTwoDown(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveRightTwoDown create() {
        return new MoveRightTwoDown(2, -1);
    }
}
