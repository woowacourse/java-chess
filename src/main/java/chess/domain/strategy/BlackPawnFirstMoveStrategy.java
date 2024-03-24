package chess.domain.strategy;

import chess.domain.MoveRange;

public class BlackPawnFirstMoveStrategy extends PawnMoveStrategy {
    private static final MoveRange STRAIGHT_MOVE_RANGE = new MoveRange(1, 2);
    private static final MoveRange DIAGONAL_MOVE_RANGE = new MoveRange(1, 1);

    public BlackPawnFirstMoveStrategy() {
        super(STRAIGHT_MOVE_RANGE, DIAGONAL_MOVE_RANGE);
    }
}
