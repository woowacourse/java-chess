package chess.domain.movingStrategy;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;

public final class MoveLeftUp implements MovingStrategy {

    private final static MoveLeftUp INSTANCE = new MoveLeftUp();

    private MoveLeftUp() {
    }

    public static MoveLeftUp instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return (source.getFileOrder() > target.getFileOrder() && source.getRankOrder() < target.getRankOrder()) &&
                (source.getFileOrder() - target.getFileOrder() == target.getRankOrder() - source.getRankOrder());
    }

    @Override
    public Position move(final Position currentPosition) {
        final int fileOrder = currentPosition.getFileOrder() - 1;
        final int rankOrder = currentPosition.getRankOrder() + 1;
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    @Override
    public boolean isAttackStrategy() {
        return true;
    }
}
