package chess.strategy;

import chess.domain.Position;

public interface MoveStrategy {

    boolean isMovable(Position source, Position target);

}
