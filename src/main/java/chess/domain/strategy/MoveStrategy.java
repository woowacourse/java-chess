package chess.domain.strategy;

import chess.domain.position.Position;

public interface MoveStrategy {

    boolean isMovable(Position from, Position to);
}
