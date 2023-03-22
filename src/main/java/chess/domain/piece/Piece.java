package chess.domain.piece;

import chess.domain.board.KnightMove;
import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.Objects;

public abstract class Piece {
    protected final Team team;
    protected final Role role;

    public Piece(final Team team, final Role role) {
        this.team = team;
        this.role = role;
    }

    public boolean isMovable(final Square source, final Square target, final Move move) {
        throw new UnsupportedOperationException();
    }

    public boolean isMovable(final Square source, final Square target, final KnightMove move) {
        throw new UnsupportedOperationException();
    }

    public boolean isSameRole(final Role role) {
        return this.role == role;
    }

    public boolean isSameCamp(final Team team) {
        return this.team == team;
    }

    public boolean isAnotherCamp(final Team team) {
        return this.team != team;
    }

    public Team getCamp() {
        return this.team;
    }

    public Role getRole() {
        return this.role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return this.team == piece.team && this.role == piece.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.team, this.role);
    }
}
