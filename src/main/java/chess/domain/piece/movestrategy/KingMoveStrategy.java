package chess.domain.piece.movestrategy;

import chess.domain.board.MoveOrder;
import chess.domain.board.Direction;

public class KingMoveStrategy extends MoveStrategy {

    private static final int ONE_STEP = 1;

    public KingMoveStrategy() {
        super(Direction.EVERY_DIRECTION);
    }

    @Override
    public boolean canMove(final MoveOrder moveOrder) {
        if (directions.contains(moveOrder.getDirection())) {
            return moveOrder.getStepCount() == ONE_STEP;
        }
        return false;
    }
}
