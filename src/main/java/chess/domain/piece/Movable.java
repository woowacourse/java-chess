package chess.domain.piece;

import chess.domain.piece.move.Position;

public interface Movable {
    boolean canMove(final Position source, final Position target);

    boolean canAttack(final Position source, final Position target);
}
