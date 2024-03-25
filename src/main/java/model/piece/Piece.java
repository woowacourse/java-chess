package model.piece;

import java.util.Objects;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public abstract class Piece {

    private final Camp camp;

    protected Piece(final Camp camp) {
        this.camp = camp;
    }

    public abstract Set<Position> getMoveRoute(final Moving moving);

    protected abstract boolean canMovable(final Moving moving);

    public Set<Position> getAttackRoute(final Moving moving) {
        return getMoveRoute(moving);
    }

    public Camp getCamp() {
        return camp;
    }

    public boolean isSameCamp(final Camp target) {
        return camp == target;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (target == null || getClass() != target.getClass()) {
            return false;
        }
        Piece piece = (Piece) target;
        return camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp);
    }
}
