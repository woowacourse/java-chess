package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Pawn extends Piece {

    private static final double PAWN_SCORE = 1;
    private static final int RANGE = 1;
    private static final int INITIAL_PAWN_MOVE_RANGE = 2;

    public Pawn(Color color) {
        super(color, PAWN_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        if (color == Color.WHITE) {
            return isAllColorMovable(fromPosition, toPosition)
                && Direction.judge(fromPosition, toPosition) == Direction.UP;
        }
        return isAllColorMovable(fromPosition, toPosition)
            && Direction.judge(fromPosition, toPosition) == Direction.DOWN;
    }

    private boolean isAllColorMovable(Position fromPosition, Position toPosition) {
        if (isInitialPosition(fromPosition)) {
            return isMovableRange(fromPosition, toPosition, RANGE)
                || isMovableRange(fromPosition, toPosition, INITIAL_PAWN_MOVE_RANGE);
        }
        return isMovableRange(fromPosition, toPosition, RANGE);
    }

    private boolean isInitialPosition(Position fromPosition) {
        return fromPosition.isSameRank(Rank.TWO)
            || fromPosition.isSameRank(Rank.SEVEN);
    }

    private boolean isMovableRange(Position fromPosition, Position toPosition, int range) {
        return fromPosition.isSameFile(toPosition)
            && Math.abs(fromPosition.getRankDifference(toPosition)) == range;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        if (color == Color.WHITE) {
            return isAllColorCatchable(fromPosition, toPosition)
                && isWhiteCatchableDirection(Direction.judge(fromPosition, toPosition));
        }
        return isAllColorCatchable(fromPosition, toPosition)
            && isBlackCatchableDirection(Direction.judge(fromPosition, toPosition));
    }

    private boolean isAllColorCatchable(Position fromPosition, Position toPosition) {
        return fromPosition.isDiagonal(toPosition)
            && Math.abs(fromPosition.getFileDifference(toPosition)) == RANGE
            && Math.abs(fromPosition.getRankDifference(toPosition)) == RANGE;
    }

    private boolean isWhiteCatchableDirection(Direction direction) {
        return direction == Direction.LEFTUP || direction == Direction.RIGHTUP;
    }

    private boolean isBlackCatchableDirection(Direction direction) {
        return direction == Direction.LEFTDOWN || direction == Direction.RIGHTDOWN;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
