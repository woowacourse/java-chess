package chess.domain.piece;

import chess.domain.board.Square;

public abstract class Piece {
    protected final Team team;
    protected final Role role;

    public Piece(Team team, Role role) {
        this.team = team;
        this.role = role;
    }

    public abstract void validateMovableRange(Square source, Square target);

    public boolean isSameRole(Role role) {
        return this.role == role;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public boolean isAnotherTeam(Team team) {
        return this.team != team;
    }

    public boolean isEmpty() {
        return false;
    }

    public Team getTeam() {
        return team;
    }

    public Role getRole() {
        return role;
    }
}
