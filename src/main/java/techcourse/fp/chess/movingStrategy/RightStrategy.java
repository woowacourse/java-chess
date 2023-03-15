package techcourse.fp.chess.movingStrategy;

import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;

public class RightStrategy implements MovingStrategy{

    @Override
    public boolean movable(final Position source, final Position target) {
        return source.getFileOrder() < target.getFileOrder() && source.getRankOrder() == target.getRankOrder();
    }

    @Override
    public Position move(final Position currentPosition) {
        final int fileOrder = currentPosition.getFileOrder() + 1;
        return Position.of(File.of(fileOrder), currentPosition.getRank());
    }
}
