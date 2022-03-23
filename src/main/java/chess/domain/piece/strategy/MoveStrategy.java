package chess.domain.piece.strategy;

import chess.domain.board.MovingOrder;
import chess.domain.piece.Color;

public interface MoveStrategy {

    void movePossibility(Color color, MovingOrder movingOrder);
}
