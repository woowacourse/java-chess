package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;

public abstract class Piece {
    protected final Team team;
    protected final Role role;

    public Piece(final Team team, final Role role) {
        this.team = team;
        this.role = role;
    }

    public abstract boolean isMovable(final Square source, final Square target, final Move move);

    public boolean isSameRole(final Role role) {
        return this.role == role;
    }

    public boolean isSameCamp(final Team team) {
        return this.team == team;
    }

    public boolean isAnotherCamp(final Team team) {
        return this.team != team;
    }

    public boolean isEmpty() {
        return false;
    }

    public Team getCamp() {
        return this.team;
    }

    public Role getRole() {
        return this.role;
    }
}
