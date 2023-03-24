package chess.domain.movingStrategy;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;

public final class MoveRightUp implements MovingStrategy {

    private final static MoveRightUp INSTANCE = new MoveRightUp();

    private MoveRightUp() {
    }

    public static MoveRightUp instance() {
        return INSTANCE;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return (source.getFileOrder() < target.getFileOrder() && source.getRankOrder() < target.getRankOrder()) &&
                (target.getFileOrder() - source.getFileOrder() == target.getRankOrder() - source.getRankOrder());
    }

    @Override
    public Position move(final Position currentPosition) {
        final int fileOrder = currentPosition.getFileOrder() + 1;
        final int rankOrder = currentPosition.getRankOrder() + 1;
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }

    @Override
    public boolean isAttackStrategy() {
        return true;
    }
}
