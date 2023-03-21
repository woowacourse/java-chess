package chess.domain.movingStrategy;

import chess.domain.File;
import chess.domain.Position;

public final class MoveRight implements MovingStrategy{

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
