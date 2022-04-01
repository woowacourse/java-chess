package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Pawn extends Piece {

    private static final double PAWN_SCORE = 1;
    private static final int RANGE = 1;
    private static final int INITIAL_PAWN_MOVE_RANGE = 2;

    public Pawn(final Color color) {
        super(color, PAWN_SCORE);
    }

    @Override
    public boolean isMovable(final Position source, final Position target) {
        if (color == Color.WHITE) {
            return isAllColorMovable(source, target)
                && Direction.judge(source, target) == Direction.UP;
        }
        return isAllColorMovable(source, target)
            && Direction.judge(source, target) == Direction.DOWN;
    }

    private boolean isAllColorMovable(final Position source, final Position target) {
        if (isInitialPosition(source)) {
            return isMovableRange(source, target, RANGE)
                || isMovableRange(source, target, INITIAL_PAWN_MOVE_RANGE);
        }
        return isMovableRange(source, target, RANGE);
    }

    private boolean isInitialPosition(final Position source) {
        return source.isSameRank(Rank.TWO)
            || source.isSameRank(Rank.SEVEN);
    }

    private boolean isMovableRange(final Position source, final Position target, final int range) {
        return source.isSameFile(target)
            && Math.abs(source.getRankDifference(target)) == range;
    }

    @Override
    public boolean isCatchable(final Position source, final Position target) {
        if (color == Color.WHITE) {
            return isAllColorCatchable(source, target)
                && isWhiteCatchableDirection(Direction.judge(source, target));
        }
        return isAllColorCatchable(source, target)
            && isBlackCatchableDirection(Direction.judge(source, target));
    }

    private boolean isAllColorCatchable(final Position source, final Position target) {
        return source.isDiagonal(target)
            && Math.abs(source.getFileDifference(target)) == RANGE
            && Math.abs(source.getRankDifference(target)) == RANGE;
    }

    private boolean isWhiteCatchableDirection(final Direction direction) {
        return direction == Direction.LEFTUP || direction == Direction.RIGHTUP;
    }

    private boolean isBlackCatchableDirection(final Direction direction) {
        return direction == Direction.LEFTDOWN || direction == Direction.RIGHTDOWN;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
