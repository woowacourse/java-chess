package chess.domain.movingStrategy;

import chess.domain.game.File;
import chess.domain.game.Position;

public final class MoveRight implements MovingStrategy{

    private final static MoveRight INSTANCE = new MoveRight();

    private MoveRight() {
    }

    public static MoveRight instance() {
        return INSTANCE;
    }

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
