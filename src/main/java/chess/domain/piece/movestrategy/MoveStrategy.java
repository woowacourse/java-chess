package chess.domain.piece.movestrategy;

import chess.domain.board.MoveOrder;
import chess.domain.board.Direction;

import java.util.List;

public abstract class MoveStrategy {

    protected final List<Direction> directions;

    public MoveStrategy(final List<Direction> directions) {
        this.directions = directions;
    }

    public abstract boolean canMove(final MoveOrder moveOrder);
}
