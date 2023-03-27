package chess.domain.piece;

import chess.domain.side.Color;

public class Rook extends Piece {
    public Rook(final Color color, final Role role) {
        super(color, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isStraight();
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
