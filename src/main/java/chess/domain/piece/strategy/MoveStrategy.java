package chess.domain.piece.strategy;

import chess.domain.square.Square;

public interface MoveStrategy {

    boolean movable(Square current, Square destination);
    void move(Square current, Square destination);
}
