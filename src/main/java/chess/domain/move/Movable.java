package chess.domain.move;

import chess.domain.piece.Position;

@FunctionalInterface
public interface Movable {
    boolean canMove(final Position source, final Position target);
}
