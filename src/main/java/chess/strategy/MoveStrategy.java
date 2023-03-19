package chess.strategy;

import chess.domain.Position;

public interface MoveStrategy {

    boolean canMove(Position source, Position target);

}
