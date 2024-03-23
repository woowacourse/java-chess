package domain.piece;

import domain.Team;
import domain.square.Square;

public abstract class Piece {

    protected final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public abstract boolean canMove(Square source, Square target);

    public abstract boolean canAttack(final Square source, final Square target);

    public final boolean canNotMove(final Square source, final Square target) {
        return !canMove(source, target);
    }

    public final boolean canNotAttack(final Square source, final Square target) {
        return !canAttack(source, target);
    }

    public final boolean isSameTeam(final Piece other) {
        return this.team == other.team;
    }

    public final boolean isOppositeTeam(final Team other) {
        return team != other;
    }

    public abstract boolean equals(final Object o);

    public abstract int hashCode();
}
