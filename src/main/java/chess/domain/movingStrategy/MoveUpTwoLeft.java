package chess.domain.movingStrategy;

public final class MoveUpTwoLeft extends KnightMovingStrategy {

    private MoveUpTwoLeft(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveUpTwoLeft create() {
        return new MoveUpTwoLeft(-1, 2);
    }
}
