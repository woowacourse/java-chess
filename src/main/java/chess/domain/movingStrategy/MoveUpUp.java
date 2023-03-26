package chess.domain.movingStrategy;

import chess.domain.game.Position;
import chess.domain.game.Rank;

public class MoveUpUp implements MovingStrategy {

    private static final MoveUpUp INSTANCE = new MoveUpUp();

    private MoveUpUp() {
    }

    public static MoveUpUp instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() == target.getFileOrder() && target.getRankOrder() - source.getRankOrder() == 2;
    }

    @Override
    public Position move(final Position currentPosition) {
        final int rankOrder = currentPosition.getRankOrder() + 2;
        return Position.of(currentPosition.getFile(), Rank.of(rankOrder));
    }
}
