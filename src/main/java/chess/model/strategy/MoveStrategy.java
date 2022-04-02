package chess.model.strategy;

import chess.model.MoveType;
import chess.model.position.Position;

public interface MoveStrategy {
    boolean movable(Position source, Position target, MoveType moveType);
}
