package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Role;
import chess.domain.Side;

public abstract class MovablePiece extends Piece {
    protected final Side side;
    protected final Role role;

    public MovablePiece(final Side side, final Role role) {
        this.side = side;
        this.role = role;
    }

    public boolean isSameSide(final Side side) {
        return this.side.equals(side);
    }

    protected boolean isOpponentSide(final MovablePiece targetPiece) {
        return !side.equals(targetPiece.side);
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

    public abstract boolean canAttack(final Direction direction, final int distance, final MovablePiece piece);

    public abstract boolean canMove(final Direction direction, final int distance);
}
