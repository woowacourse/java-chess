package chess.domain.movingStrategy;

public final class MoveDownTwoLeft extends KnightMovingStrategy {

    private MoveDownTwoLeft(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static MoveDownTwoLeft create() {
        return new MoveDownTwoLeft(-1, -2);
    }
}
