package chess.domain.move;

import chess.domain.piece.Position;

public interface Movable {
    boolean canMove(final Position source, final Position target);

    boolean canAttack(final Position source, final Position target);

    boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible);
}
