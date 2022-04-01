package chess.domain.piece.movestrategy;

import chess.domain.board.MoveOrder;
import chess.domain.board.Direction;

public class KnightMoveStrategy extends MoveStrategy {

    public KnightMoveStrategy() {
        super(Direction.KNIGHT_DIRECTION);
    }

    @Override
    public boolean canMove(final MoveOrder moveOrder) {
        return directions.contains(moveOrder.getDirection());
    }
}
