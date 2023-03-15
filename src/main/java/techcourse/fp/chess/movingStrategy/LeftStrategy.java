package techcourse.fp.chess.movingStrategy;

import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;

public class LeftStrategy implements MovingStrategy {

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() > target.getFileOrder() && source.getRankOrder() == target.getRankOrder();
    }

    @Override
    public Position move(final Position currentPosition) {
        final int file = currentPosition.getFileOrder() - 1;
        return Position.of(File.of(file), currentPosition.getRank());
    }
}

