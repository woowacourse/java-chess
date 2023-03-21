package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;

public class BlackPawnMovementStrategy extends PawnMovementStrategy {

    public BlackPawnMovementStrategy(final Color color, final Rank permitTwoMoveRank) {
        super(color, permitTwoMoveRank);
    }

    @Override
    protected void validateAdditionalConstraint(final PiecePosition source,
                                                final PiecePosition destination) {
        if (!isDestinationRelativelySouth(source, destination)) {
            throw new IllegalArgumentException("검정 폰은 남쪽을 향해서만 이동할 수 있습니다.");
        }
    }
}
