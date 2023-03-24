package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public abstract class Piece {

    protected final Team team;
    protected final Role role;

    protected Piece(final Team team, final Role role) {
        this.team = team;
        this.role = role;
    }

    public abstract boolean canMove(Position source, Position destination);

    public abstract boolean canAttack(Position source, Position destination);

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public boolean isSameRole(final Role role) {
        return this.role == role;
    }

    public Team getTeam() {
        return team;
    }

    public double getScore() {
        return role.getScore();
    }
}
