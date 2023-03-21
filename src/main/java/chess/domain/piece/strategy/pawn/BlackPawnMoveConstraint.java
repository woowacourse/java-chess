package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.PiecePosition;

public class BlackPawnMoveConstraint implements PawnMoveConstraint {

    @Override
    public void validateConstraint(final PiecePosition source,
                                   final PiecePosition destination) {
        if (!isDestinationRelativelySouth(source, destination)) {
            throw new IllegalArgumentException("검정 폰은 남쪽을 향해서만 이동할 수 있습니다.");
        }
    }

    private boolean isDestinationRelativelySouth(final PiecePosition source, final PiecePosition destination) {
        return source.rankInterval(destination) < 0;
    }
}
