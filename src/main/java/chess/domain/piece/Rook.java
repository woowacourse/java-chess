package chess.domain.piece;

import chess.domain.side.Side;

public class Rook extends Piece {
    public Rook(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isStraight();
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }
}
