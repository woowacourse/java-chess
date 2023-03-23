package chess.domain.movingStrategy;

import chess.domain.File;
import chess.domain.Position;

public final class MoveLeft implements MovingStrategy {

    private final static MoveLeft INSTANCE = new MoveLeft();

    private MoveLeft() {
    }

    public static MoveLeft get() {
        return INSTANCE;
    }

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

