package chess.model;

public class WhitePawn extends AbstractPawn {

    public static final int ONE_STEP = 1;
    public static final int TWO_STEP = 2;
    public static final int WHITE_START_Y_POSITION = 2;

    public WhitePawn() {
        super(ChessPieceColor.WHITE);
    }

    @Override
    protected boolean isVerticalOneStep(final Point source, final Point target) {
        return Direction.N == Direction.valueOf(source, target) && source.calculateYsDiff(target) == ONE_STEP;
    }

    @Override
    protected boolean isVerticalTwoStep(final Point source, final Point target) {
        return Direction.N == Direction.valueOf(source, target) && isStartPoint(source) && source.calculateYsDiff(target) == TWO_STEP;
    }

    @Override
    protected boolean isDiagonalOneStep(final Point source, final Point target) {
        Direction direction = Direction.valueOf(source, target);
        return (Direction.NE == direction || Direction.NW == direction)
                && source.calculateYsDiff(target) == ONE_STEP && source.calculateXsDiff(target) == ONE_STEP;

    }

    private boolean isStartPoint(final Point source) {
        return source.isSameY(WHITE_START_Y_POSITION);
    }
}
