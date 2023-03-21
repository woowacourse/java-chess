package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Side;

public class King extends MovablePiece {
    private static final int MAX_MOVE_DISTANCE = 1;

    public King(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return (direction.isDiagonal() || direction.isStraight()) && distance == MAX_MOVE_DISTANCE;
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final MovablePiece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }
}
