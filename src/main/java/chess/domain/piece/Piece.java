package chess.domain.piece;

import chess.domain.Position;
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

    public abstract boolean canMove(Position source, Position target);

    protected boolean canMoveDiagonal(Position source, Position target) {
        int xDistance = source.getX() - target.getX();
        int yDistance = source.getY() - target.getY();
        if (xDistance == 0 || yDistance == 0) {
            return false;
        }
        return Math.abs(yDistance / xDistance) == 1;
    }

    protected boolean canMoveCross(Position source, Position target) {
        if (source.isSameX(target) && !source.isSameY(target)) {
            return true;
        }
        if (!source.isSameX(target) && source.isSameY(target)) {
            return true;
        }
        return false;
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
