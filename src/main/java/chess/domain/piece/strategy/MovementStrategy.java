package chess.domain.piece.strategy;

import chess.domain.position.Position;

public interface MovementStrategy {

    boolean isMovable(final Position source, final Position target);
}
