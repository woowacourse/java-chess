package chess.domain.movingStrategy;

import chess.domain.game.Position;
import chess.domain.game.Rank;

public class MoveDownDown implements MovingStrategy {

    private final static MoveDownDown INSTANCE = new MoveDownDown();

    private MoveDownDown() {
    }

    public static MoveDownDown instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() == target.getFileOrder() && source.getRankOrder() - target.getRankOrder() == 2;
    }

    @Override
    public Position move(final Position currentPosition) {
        final int rankOrder = currentPosition.getRankOrder() - 2;
        return Position.of(currentPosition.getFile(), Rank.of(rankOrder));
    }
}

