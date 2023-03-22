package chess.domain.piece;

import chess.domain.square.Color;
import chess.domain.square.Side;

public abstract class Piece {
    protected final Side side;
    protected final Role role;

    public Piece(final Side side, final Role role) {
        this.side = side;
        this.role = role;
    }

    public boolean isSameSide(final Side side) {
        return this.side == side;
    }

    public boolean isOpposite(final Side side) {
        return this.side != side;
    }

    public boolean hasSameRole(final Role role) {
        return this.role == role;
    }

    public Piece currentState() {
        return this;
    }

    public Side getSide() {
        return side;
    }

    public Role getRole() {
        return role;
    }

    public Color getColor() {
        return side.getColor();
    }

    public abstract boolean canAttack(final Direction direction, final int distance, final Piece targetPiece);

    public abstract boolean canMove(final Direction direction, final int distance);
}
