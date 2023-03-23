package chess.domain.movingStrategy;

public final class MoveRightTwoDown extends KnightMovingStrategy {
    private final static MoveRightTwoDown INSTANCE = new MoveRightTwoDown(2, -1);

    private MoveRightTwoDown(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveRightTwoDown get() {
        return INSTANCE;
    }
}
