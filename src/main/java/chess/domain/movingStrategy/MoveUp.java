package chess.domain.movingStrategy;

import chess.domain.Position;
import chess.domain.Rank;

public final class MoveUp implements MovingStrategy {

    private final static MoveUp INSTANCE = new MoveUp();

    private MoveUp() {
    }

    public static MoveUp get() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() == target.getFileOrder() && source.getRankOrder() < target.getRankOrder();
    }

    @Override
    public Position move(final Position currentPosition) {
        final int rankOrder = currentPosition.getRankOrder() + 1;
        return Position.of(currentPosition.getFile(), Rank.of(rankOrder));
    }
}
