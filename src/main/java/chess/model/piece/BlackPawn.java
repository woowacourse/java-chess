package chess.model.piece;

import chess.model.Direction;
import chess.model.Point;

public class BlackPawn extends AbstractPawn {
    private static final int BLACK_START_Y_POSITION = 7;

    public BlackPawn() {
        super(ChessPieceColor.BLACK);
    }

    @Override
    protected boolean isVerticalOneStep(final Point source, final Point target) {
        return Direction.S == Direction.valueOf(source, target) && source.calculateYsDiff(target) == NORMAL_STEP_SIZE;
    }

    @Override
    protected boolean isVerticalTwoStep(final Point source, final Point target) {
        return Direction.S == Direction.valueOf(source, target) && isStartPoint(source) && source.calculateYsDiff(target) == START_POINT_STEP_SIZE;
    }

    @Override
    protected boolean isDiagonalOneStep(final Point source, final Point target) {
        Direction direction = Direction.valueOf(source, target);
        return (Direction.SE == direction || Direction.SW == direction)
                && source.calculateYsDiff(target) == NORMAL_STEP_SIZE && source.calculateXsDiff(target) == NORMAL_STEP_SIZE;

    }

    private boolean isStartPoint(final Point source) {
        return source.isSameY(BLACK_START_Y_POSITION);
    }
}
