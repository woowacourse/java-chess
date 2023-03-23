package chess.domain.piece;

import chess.domain.square.Side;

public class Queen extends Piece {
    public Queen(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isDiagonal() || direction.isStraight();
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece targetPiece) {
        return canMove(direction, distance) && isOpposite(targetPiece.side);
    }
}
