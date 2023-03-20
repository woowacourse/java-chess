package chess.domain.piece.move.piece;

import chess.domain.piece.Position;

public interface MoveRule {
    boolean canMove(final Position source, final Position target);

    boolean canAttack(final Position source, final Position target);

    boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible);
}
