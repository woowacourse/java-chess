package chess.domain.piece;

import chess.domain.position.Position;

@FunctionalInterface
public interface Movable {
    boolean canMove(final Position source, final Position target);
}
