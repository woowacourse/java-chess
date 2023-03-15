package techcourse.fp.movingStrategy;

import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;

public class DownStrategy implements MovingStrategy {

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() == target.getFileOrder() && source.getRankOrder() > target.getRankOrder();
    }

    @Override
    public Position move(final Position currentPosition) {
        final int rank = currentPosition.getRankOrder() - 1;
        return Position.of(currentPosition.getFile(), Rank.of(rank));
    }
}
