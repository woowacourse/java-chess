package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.Path;

public class WhitePawnMoveStrategy extends PawnMoveStrategy {

    @Override
    protected boolean satisfyAdditionalConstraint(final Path path) {
        return path.isDestinationRelativelyNorth();
    }
}
