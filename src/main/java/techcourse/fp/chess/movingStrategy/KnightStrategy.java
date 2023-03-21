package techcourse.fp.chess.movingStrategy;

import techcourse.fp.chess.domain.Position;

public class KnightStrategy implements MovingStrategy {

    @Override
    public boolean movable(final Position source, final Position target) {
        final int fileOrder = source.getFileOrder() - target.getFileOrder();
        final int rankOrder = source.getRankOrder() - target.getRankOrder();
        return KnightMove.isMovable(fileOrder, rankOrder);
    }

    @Override
    public Position move(final Position currentPosition) {
        return null;
    }
}
