package chess.domain.strategy.movestrategy;

import chess.domain.position.Position;

public interface MoveStrategy {

    boolean isMovable(Position from, Position to);
}
