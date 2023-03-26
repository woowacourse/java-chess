package chess.domain.movingStrategy;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;

public final class MoveLeftDown implements MovingStrategy {

    private static final MoveLeftDown INSTANCE = new MoveLeftDown();

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
}
