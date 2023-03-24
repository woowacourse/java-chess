package chess.domain.movingStrategy;

import chess.domain.Position;
import chess.domain.Rank;

public final class MoveDown implements MovingStrategy {

    private final static MoveDown INSTANCE = new MoveDown();

    private MoveDown() {
    }

    public static MoveDown instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() == target.getFileOrder() && source.getRankOrder() > target.getRankOrder();
    }

    @Override
    public Position move(final Position currentPosition) {
        final int rankOrder = currentPosition.getRankOrder() - 1;
        return Position.of(currentPosition.getFile(), Rank.of(rankOrder));
    }
}
