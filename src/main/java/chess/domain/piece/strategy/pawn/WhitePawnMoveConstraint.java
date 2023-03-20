package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.PiecePosition;

public class WhitePawnMoveConstraint implements PawnMoveConstraint {

    @Override
    public void validateConstraint(final PiecePosition source,
                                   final PiecePosition destination) {
        if (!isDestinationRelativelyNorth(source, destination)) {
            throw new IllegalArgumentException("흰 폰은 북쪽을 향해서만 이동할 수 있습니다.");
        }
    }

    private boolean isDestinationRelativelyNorth(final PiecePosition source, final PiecePosition destination) {
        return source.rankInterval(destination) > 0;
    }
}
