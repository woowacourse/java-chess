package chess.domain.piece.type.pawn.move;

import chess.domain.piece.Color;
import chess.domain.piece.position.Path;

public interface PawnColorMoveStrategy {

    void validateMovementDirection(final Path path);

    // TODO 인터페이스에서 색깔에 따라 하위 구현체를 정해주는 것이 괜찮을까요?
    static PawnColorMoveStrategy byColor(final Color color) {
        if (color.isWhite()) {
            return new WhitePawnMoveStrategy();
        }
        return new BlackPawnMoveStrategy();
    }
}
