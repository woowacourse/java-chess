package chess.domain.piece;

import chess.domain.side.Color;
import chess.domain.side.Side;

public abstract class Piece {
    protected final Side side;
    protected final Role role;

    public Piece(Side side, Role role) {
        this.side = side;
        this.role = role;
    }

    public boolean isOpponentSide(final Piece targetPiece) {
        if (side.equals(Side.from(Color.NOTHING))) {
            return false;
        }
        return side.findOpponent().equals(targetPiece.side);
    }

    public boolean isRole(Role role) {
        return this.role.equals(role);
    }

    public boolean isNotVacant() {
        return this.role != Role.VACANT_PIECE;
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

    public abstract boolean canMove(final Direction direction, final int distance);

    public abstract boolean canAttack(final Direction direction, final int distance, final Piece target);

    public abstract Piece update();
}
