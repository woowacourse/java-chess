package chess.domain.piece;

import chess.domain.side.Color;

public class Bishop extends Piece {
    public Bishop(final Color color, final Role role) {
        super(color, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isDiagonal();
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }

    @Override
    public Piece update() {
        return this;
    }
}
