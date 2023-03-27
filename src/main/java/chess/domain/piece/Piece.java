package chess.domain.piece;

import chess.domain.square.Color;
import chess.domain.square.Team;

public abstract class Piece {
    protected final Team team;
    protected final Role role;

    public Piece(final Team team, final Role role) {
        this.team = team;
        this.role = role;
    }

    public boolean isSameSide(final Team team) {
        return this.team == team;
    }

    public boolean isOpposite(final Team team) {
        return this.team != team;
    }

    public boolean hasSameRole(final Role role) {
        return this.role == role;
    }

    public Piece nextState() {
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public Role getRole() {
        return role;
    }

    public Color getColor() {
        return team.getColor();
    }

    public abstract boolean canAttack(final Direction direction, final int distance, final Piece targetPiece);

    public abstract boolean canMove(final Direction direction, final int distance);

    public double getScore() {
        return role.getScore();
    }
}
