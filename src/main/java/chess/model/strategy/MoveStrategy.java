package chess.model.strategy;

import chess.model.Position;

public interface MoveStrategy {
    boolean movable(Position source, Position target, boolean isKill);
}
