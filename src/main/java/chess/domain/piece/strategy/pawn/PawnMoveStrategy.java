package chess.domain.piece.strategy.pawn;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

public abstract class PawnMoveStrategy implements MoveStrategy {

    @Override
    public void validatePath(final Path path) {
        validAdditionalConstraint(path);
        if (path.rankInterval() == 0) {
            throw new IllegalArgumentException("폰의 움직임은 Rank 가 증가하거나 감소해야 합니다.");
        }
        if (!path.isUnitDistance() && !isTwoVerticalMove(path)) {
            throw new IllegalArgumentException("폰은 그렇게 움직일 수 없습니다.");
        }
    }

    protected abstract void validAdditionalConstraint(final Path path);

    private boolean isTwoVerticalMove(final Path path) {
        if (Math.abs(path.rankInterval()) != 2) {
            return false;
        }
        return Math.abs(path.fileInterval()) == 0;
    }
}
