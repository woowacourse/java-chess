package techcourse.fp.chess.movingStrategy;

public class DownTwoLeft extends KnightMovingStrategy {

    private DownTwoLeft(final int horizontalMovement, final int verticalMovement) {
        super(horizontalMovement, verticalMovement);
    }

    public static DownTwoLeft create() {
        return new DownTwoLeft(-1, -2);
    }
}
