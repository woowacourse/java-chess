package chess.domain.piece.strategy.pawn;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;

public abstract class PawnMoveStrategy implements MoveStrategy {

    @Override
    public void validatePath(final Path path) {
        validAdditionalConstraint(path);
        if (path.isHorizontal()) {
            throw new IllegalArgumentException("폰은 수평으로 움직일 수 없습니다.");
        }
        if (!path.isUnitDistance() && !path.isTwoVerticalMove()) {
            throw new IllegalArgumentException("폰은 그렇게 움직일 수 없습니다.");
        }
    }

    protected abstract void validAdditionalConstraint(final Path path);
}
