package techcourse.fp.chess.movingStrategy;

import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;

public final class MoveLeftUp implements MovingStrategy {

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
}
