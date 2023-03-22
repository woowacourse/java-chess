package chess.domain.piece;

import chess.domain.square.Color;
import chess.domain.square.Side;

public class InitialPawn extends Pawn {
    private static final int MOVE_DISTANCE = 2;

    public InitialPawn(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        if (distance > MOVE_DISTANCE) {
            return false;
        }
        if (this.side == Side.from(Color.WHITE)) {
            return WHITE_FORWARD_DIRECTION == direction;
        }
        return BLACK_FORWARD_DIRECTION == direction;
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece targetPiece) {
        return super.canAttack(direction, distance, targetPiece);
    }

    @Override
    public Piece currentState() {
        return Role.PAWN.create(side);
    }
}
