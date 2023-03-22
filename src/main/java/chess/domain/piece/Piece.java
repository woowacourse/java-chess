package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.strategy.MoveStrategy;

import java.util.Objects;

public abstract class Piece {

    protected final Role role;
    protected final Team team;
    protected final MoveStrategy moveStrategy;

    protected Piece(Role role, Team team, MoveStrategy moveStrategy) {
        this.role = role;
        this.team = team;
        this.moveStrategy = moveStrategy;
    }

    public abstract boolean canMove(Position source, Position target);

    public boolean isSameTeamWith(Piece other) {
        return this.team == other.team;
    }

    public boolean isRoleOf(Role role) {
        return this.role == role;
    }

    public Role getRole() {
        return role;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) o;
        return team == piece.team && role == piece.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, role);
    }
}
