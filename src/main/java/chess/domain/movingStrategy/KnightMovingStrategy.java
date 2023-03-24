package chess.domain.movingStrategy;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;

public abstract class KnightMovingStrategy implements MovingStrategy {

    private final int horizontalMovement;
    private final int verticalMovement;

    KnightMovingStrategy(final int horizontalMovement, final int verticalMovement) {
        this.horizontalMovement = horizontalMovement;
        this.verticalMovement = verticalMovement;
    }

    @Override
    public boolean movable(final Position source, final Position target) {
        return target.getFileOrder() - source.getFileOrder() == horizontalMovement &&
                target.getRankOrder() - source.getRankOrder() == verticalMovement;
    }

    @Override
    public Position move(final Position currentPosition) {
        final int fileOrder = currentPosition.getFileOrder() + horizontalMovement;
        final int rankOrder = currentPosition.getRankOrder() + verticalMovement;
        return Position.of(File.of(fileOrder), Rank.of(rankOrder));
    }
}
