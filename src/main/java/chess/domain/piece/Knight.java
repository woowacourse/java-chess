package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Side;

public class Knight extends Piece {
    private static final int MAX_MOVE_DISTANCE = 1;

    public Knight(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isKnightDirection() && distance == MAX_MOVE_DISTANCE;
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece targetPiece) {
        return canMove(direction, distance) && isOpponentSide(targetPiece);
    }
}
