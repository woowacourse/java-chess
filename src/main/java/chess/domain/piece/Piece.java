package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;
import java.util.Objects;

public abstract class Piece {
    protected final Role role;
    protected final Team team;

    protected Piece(Role role, Team team) {
        this.role = role;
        this.team = team;
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
