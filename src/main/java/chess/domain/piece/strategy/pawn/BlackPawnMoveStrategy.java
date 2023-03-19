package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.Path;

public class BlackPawnMoveStrategy extends PawnMoveStrategy {

    @Override
    protected void validAdditionalConstraint(final Path path) {
        if (!path.isDestinationRelativelySouth()) {
            throw new IllegalArgumentException("검정 폰은 남쪽을 향해서만 이동할 수 있습니다.");
        }
    }
}
