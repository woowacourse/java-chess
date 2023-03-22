package chess.domain.piece;

import chess.domain.position.Position;

@FunctionalInterface
public interface Movable {
    Move move = new Move();

    boolean canMove(final Position source, final Position target);
}
