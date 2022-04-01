package chess.model.strategy;

import chess.model.position.Position;

public interface MoveStrategy {
    boolean movable(Position source, Position target, boolean isKill);
}
