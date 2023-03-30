package chess.strategy;

import chess.domain.board.Position;

public interface MoveStrategy {

    boolean isMovable(Position source, Position target);

}
