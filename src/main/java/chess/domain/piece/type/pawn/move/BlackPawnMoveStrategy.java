package chess.domain.piece.type.pawn.move;

import chess.domain.piece.position.Path;

public class BlackPawnMoveStrategy implements PawnColorMoveStrategy {

    @Override
    public void validateMovementDirection(final Path path) {
        if (!path.isDestinationRelativelySouth()) {
            throw new IllegalArgumentException("검정색 폰은 남쪽으로만 이동할 수 있습니다.");
        }
    }
}
