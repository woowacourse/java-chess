package chess.domain.strategy;

import chess.domain.MoveRange;

public class WhitePawnFirstMoveStrategy extends PawnMoveStrategy {
    private static final MoveRange STRAIGHT_MOVE_RANGE = new MoveRange(-2, -1);
    private static final MoveRange DIAGONAL_MOVE_RANGE = new MoveRange(-1, -1);

    public WhitePawnFirstMoveStrategy() {
        super(STRAIGHT_MOVE_RANGE, DIAGONAL_MOVE_RANGE);
    }
}
