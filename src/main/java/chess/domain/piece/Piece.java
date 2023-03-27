package chess.domain.piece;

import chess.domain.side.Color;

public abstract class Piece {
    protected final Color color;
    protected final Role role;

    public Piece(Color color, Role role) {
        this.color = color;
        this.role = role;
    }

    public boolean isOpponentSide(final Piece targetPiece) {
        if (color.equals(Color.NOTHING)) {
            return false;
        }
        return color.findOpponent().equals(targetPiece.color);
    }

    public boolean isRole(Role role) {
        return this.role.equals(role);
    }

    public boolean isNotVacant() {
        return this.role != Role.VACANT_PIECE;
    }

    public Role getRole() {
        return role;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean canMove(final Direction direction, final int distance);

    public abstract boolean canAttack(final Direction direction, final int distance, final Piece target);

    public abstract Piece update();
}
