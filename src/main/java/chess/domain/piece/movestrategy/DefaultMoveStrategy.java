package chess.domain.piece.movestrategy;

import chess.domain.board.MoveOrder;
import chess.domain.board.Direction;

import java.util.List;

public class DefaultMoveStrategy extends MoveStrategy {

    public DefaultMoveStrategy(final List<Direction> directions) {
        super(directions);
    }

    @Override
    public boolean canMove(final MoveOrder moveOrder) {
        if (directions.contains(moveOrder.getDirection())) {
            return !moveOrder.hasPieceOnWay();
        }
        return false;
    }
}
