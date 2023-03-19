package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

public abstract class PawnMoveStrategy implements MoveStrategy {

    @Override
    public boolean movable(final Path path) {
        if (!satisfyAdditionalConstraint(path)) {
            return false;
        }
        if (path.rankInterval() == 0) {
            return false;
        }
        return path.isUnitDistance() || isTwoVerticalMove(path);
    }

    protected abstract boolean satisfyAdditionalConstraint(final Path path);

    private boolean isTwoVerticalMove(final Path path) {
        if (Math.abs(path.rankInterval()) != 2) {
            return false;
        }
        return Math.abs(path.fileInterval()) == 0;
    }
}
