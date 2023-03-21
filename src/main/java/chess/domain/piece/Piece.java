package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;
import chess.dto.BoardSnapshot;
import chess.strategy.MoveStrategy;

import java.util.List;
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

    public abstract boolean canMove(Position source, Position target, BoardSnapshot boardSnapshot);

    public boolean isRoleOf(Role role) {
        return this.role == role;
    }

    protected boolean isValidPieces(Piece other) {
        return !this.isRoleOf(Role.EMPTY) && !this.isSameTeamWith(other);
    }

    protected boolean hasNotCollision(Position source, Position target, BoardSnapshot boardSnapshot) {
        List<Position> routes = source.getBetweenPositions(target);
        return routes.stream()
                .map(boardSnapshot::findByPosition)
                .allMatch(piece -> piece.isRoleOf(Role.EMPTY));
    }

    private boolean isSameTeamWith(Piece other) {
        return this.team == other.team;
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
