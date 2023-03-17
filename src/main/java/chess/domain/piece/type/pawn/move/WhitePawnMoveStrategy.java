package chess.domain.piece.type.pawn.move;

import chess.domain.piece.position.Path;

public class WhitePawnMoveStrategy implements PawnColorMoveStrategy {

    @Override
    public void validateMovementDirection(final Path path) {
        if (!path.isDestinationRelativelyNorth()) {
            throw new IllegalArgumentException("흰색 폰은 북쪽으로만 이동할 수 있습니다.");
        }
    }
}
