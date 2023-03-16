package chess.domain.piece.strategy;

import chess.domain.square.Square;

public interface MoveStrategy {

    // TODO: int 값으로 하면 어떰?
    boolean canMove(Square current, Square destination);
}
