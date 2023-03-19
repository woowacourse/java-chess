package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.Path;

public class BlackPawnMoveStrategy extends PawnMoveStrategy {

    @Override
    protected boolean satisfyAdditionalConstraint(final Path path) {
        return path.isDestinationRelativelySouth();
    }
}
