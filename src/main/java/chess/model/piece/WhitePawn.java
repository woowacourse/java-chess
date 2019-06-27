package chess.model.piece;

import chess.model.Direction;
import chess.model.Point;

public class WhitePawn extends AbstractPawn {
    public static final int WHITE_START_Y_POSITION = 2;

    public WhitePawn() {
        super(ChessPieceColor.WHITE);
    }

    @Override
    protected boolean isVerticalOneStep(final Point source, final Point target) {
        return Direction.N == Direction.valueOf(source, target) && source.calculateYsDiff(target) == NORMAL_STEP_SIZE;
    }

    @Override
    protected boolean isVerticalTwoStep(final Point source, final Point target) {
        return Direction.N == Direction.valueOf(source, target) && isStartPoint(source) && source.calculateYsDiff(target) == START_POINT_STEP_SIZE;
    }

    @Override
    protected boolean isDiagonalOneStep(final Point source, final Point target) {
        Direction direction = Direction.valueOf(source, target);
        return (Direction.NE == direction || Direction.NW == direction)
                && source.calculateYsDiff(target) == NORMAL_STEP_SIZE && source.calculateXsDiff(target) == NORMAL_STEP_SIZE;

    }

    private boolean isStartPoint(final Point source) {
        return source.isSameY(WHITE_START_Y_POSITION);
    }
}
