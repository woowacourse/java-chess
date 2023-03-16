package chess.domain.piece;

import chess.domain.Side;

public class Bishop extends MovablePiece {
    public Bishop(final Side side) {
        super(side);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return isDiagonal(direction);
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final MovablePiece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }
}
