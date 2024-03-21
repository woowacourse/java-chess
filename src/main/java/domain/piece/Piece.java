package domain.piece;

import domain.Camp;
import domain.Square;

public abstract class Piece {

    protected final Camp camp;

    protected Piece(final Camp camp) {
        this.camp = camp;
    }

    public boolean canNotMove(final Square source, final Square target) {
        return !canMove(source, target);
    }

    protected abstract boolean canMove(Square source, Square target);

    public boolean canNotAttack(final Square source, final Square target) {
        return !canAttack(source, target);
    }

    protected boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }

    public boolean isSameCamp(final Piece other) {
        return this.camp == other.camp;
    }

    public boolean isOppositeCamp(final Camp other) {
        return camp != other;
    }

    public abstract boolean equals(final Object o);

    public abstract int hashCode();
}
