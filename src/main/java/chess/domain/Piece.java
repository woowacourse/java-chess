package chess.domain;

import java.util.Objects;

public class Piece {
    private final Role role;
    private final Team team;

    public Piece(Role role, Team team) {
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
        return role == piece.role && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, team);
    }
}
