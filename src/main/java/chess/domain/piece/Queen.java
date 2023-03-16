package chess.domain.piece;

import chess.domain.side.Side;

public class Queen extends MovablePiece {
    public Queen(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return isDiagonal(direction) || isStraight(direction);
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final MovablePiece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }
}
