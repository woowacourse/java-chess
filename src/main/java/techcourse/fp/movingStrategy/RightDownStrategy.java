package techcourse.fp.movingStrategy;

import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;

public class RightDownStrategy implements MovingStrategy {

    @Override
    public boolean movable(final Position source, final Position target) {
        return (source.getFileOrder() < target.getFileOrder() && source.getRankOrder() > target.getRankOrder()) &&
                (target.getFileOrder() - source.getFileOrder() == source.getRankOrder() - target.getRankOrder());
    }

    @Override
    public Position move(final Position currentPosition) {
        final int fileOrder = currentPosition.getFileOrder() + 1;
        final int rankOrder = currentPosition.getRankOrder() - 1;
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }
}
