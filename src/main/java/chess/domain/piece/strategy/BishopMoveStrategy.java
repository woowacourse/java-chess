package chess.domain.piece.strategy;

import chess.domain.order.MoveOrder;

public class BishopMoveStrategy implements MoveStrategy {
    @Override
    public boolean canMove(MoveOrder moveOrder) {
        return false;
    }
}
