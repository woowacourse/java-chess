package chess.domain.movingStrategy;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;

public final class MoveLeftDown implements MovingStrategy {

    private final static MoveLeftDown INSTANCE = new MoveLeftDown();

    private MoveLeftDown() {
    }

    public static MoveLeftDown instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return (source.getFileOrder() > target.getFileOrder() && source.getRankOrder() > target.getRankOrder()) &&
                (source.getFileOrder() - target.getFileOrder() == source.getRankOrder() - target.getRankOrder());
    }

    @Override
    public Position move(final Position currentPosition) {
        final int fileOrder = currentPosition.getFileOrder() - 1;
        final int rankOrder = currentPosition.getRankOrder() - 1;
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    @Override
    public boolean isAttackStrategy() {
        return true;
    }
}
