package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;

public abstract class Piece {
    protected final Team team;
    protected final Role role;

    public Piece(final Team team, final Role role) {
        this.team = team;
        this.role = role;
    }

    public abstract boolean isMovable(final Square source, final Square target, final Direction direction);

    public boolean isSameRole(final Role role) {
        return this.role == role;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public boolean isAnotherTeam(final Team team) {
        return this.team != team;
    }

    public boolean isEmpty() {
        return false;
    }

    public Team getTeam() {
        return this.team;
    }

    public Role getRole() {
        return this.role;
    }
}
