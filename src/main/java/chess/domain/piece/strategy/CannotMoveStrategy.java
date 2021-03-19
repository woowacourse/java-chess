package chess.domain.piece.strategy;

import chess.domain.order.MoveOrder;

public class CannotMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(MoveOrder moveOrder) {
        return false;
    }
}
