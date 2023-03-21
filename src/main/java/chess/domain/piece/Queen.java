package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Side;

public class Queen extends MovablePiece {
    public Queen(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isDiagonal() || direction.isStraight();
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final MovablePiece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }
}
