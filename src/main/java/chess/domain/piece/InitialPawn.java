package chess.domain.piece;

import chess.domain.side.Color;
import chess.domain.side.Side;

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
    public boolean canAttack(final Direction direction, final int distance, final Piece target) {
        return super.canAttack(direction, distance, target);
    }

    @Override
    public Piece update() {
        return Role.PAWN.create(this.side);
    }
}
