package chess.model;

public abstract class AbstractPawn extends AbstractChessPiece {
    public static final int START_POINT_STEP_SIZE = 2;
    public static final int NORMAL_STEP_SIZE = 1;

    public AbstractPawn(final ChessPieceColor color) {
        super(ChessPieceType.PAWN, color);
    }

    @Override
    public boolean canMove(final Point source, final Point target, final AbstractBoardNavigator navigator) {
        if (isVerticalOneStep(source, target)) {
            return navigator.getPieceAt(target) == null;
        }

        if (isVerticalTwoStep(source, target)) {
            return navigator.getPieceAt(source.moveOneStep(Direction.valueOf(source, target))) == null &&
                    navigator.getPieceAt(target) == null;
        }

        if (isDiagonalOneStep(source, target)) {
            return navigator.getPieceAt(target) != null;
        }

        return false;
    }

    protected abstract boolean isVerticalOneStep(Point source, Point target);
    protected abstract boolean isVerticalTwoStep(Point source, Point target);
    protected abstract boolean isDiagonalOneStep(Point source, Point target);
}
