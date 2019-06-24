package chess.model;

public class BlackPawn extends AbstractPawn{
    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;
    private static final int BLACK_START_Y_POSITION = 2;

    public BlackPawn() {
        super(ChessPieceColor.BLACK);
    }

    @Override
    protected boolean isVerticalOneStep(final Point source, final Point target) {
        return Direction.S == Direction.valueOf(source, target) && source.calculateYsDiff(target) == ONE_STEP;
    }

    @Override
    protected boolean isVerticalTwoStep(final Point source, final Point target) {
        return Direction.S == Direction.valueOf(source, target) && isStartPoint(source) && source.calculateYsDiff(target) == TWO_STEP;
    }

    @Override
    protected boolean isDiagonalOneStep(final Point source, final Point target) {
        Direction direction = Direction.valueOf(source, target);
        return (Direction.SE == direction || Direction.SW == direction)
                && source.calculateYsDiff(target) == ONE_STEP && source.calculateXsDiff(target) == ONE_STEP;

    }

    private boolean isStartPoint(final Point source) {
        return source.isSameY(BLACK_START_Y_POSITION);
    }
}
