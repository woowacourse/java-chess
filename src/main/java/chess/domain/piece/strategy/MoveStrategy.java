package chess.domain.piece.strategy;

import chess.domain.order.MoveOrder;

public interface MoveStrategy {
    boolean canMove(MoveOrder moveOrder);
}
