package chess.domain.movingStrategy;

import chess.domain.game.Position;
import chess.domain.game.Rank;

public final class MoveUp implements MovingStrategy {

    private static final MoveUp INSTANCE = new MoveUp();

    private MoveUp() {
    }

    public static MoveUp instance() {
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
