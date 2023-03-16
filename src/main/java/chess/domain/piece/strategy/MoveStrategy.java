package chess.domain.piece.strategy;

import chess.domain.square.Square;

public interface MoveStrategy {

    void move(Square current, Square destination);
}
