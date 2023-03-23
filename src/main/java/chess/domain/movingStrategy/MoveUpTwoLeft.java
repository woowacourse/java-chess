package chess.domain.movingStrategy;

public final class MoveUpTwoLeft extends KnightMovingStrategy {

    private static final MoveUpTwoLeft INSTANCE = new MoveUpTwoLeft(-1, 2);


    private MoveUpTwoLeft(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveUpTwoLeft get() {
        return INSTANCE;
    }
}
