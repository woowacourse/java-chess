package chess.domain.piece.strategy;

import chess.domain.order.MoveOrder;

public class PawnMoveStrategy implements MoveStrategy {

    @Override
    public boolean canMove(MoveOrder moveOrder) {
        return false;
    }
}
