package model.piece;

import model.Camp;
import model.position.Moving;
import model.position.Position;

import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected final Camp camp;
    protected final PieceName pieceName;

    protected Piece(Camp camp, PieceName pieceName) {
        this.camp = camp;
        this.pieceName = pieceName;
    }

    public abstract Set<Position> getMoveRoute(final Moving moving);

    protected abstract boolean canMovable(final Moving moving);

    public Set<Position> getAttackRoute(final Moving moving) {
        return getMoveRoute(moving);
    }

    public boolean isSameCamp(final Camp target) {
        return camp == target;
    }

    public Camp getCamp() {
        return camp;
    }

    public String getName() {
        if (camp == Camp.BLACK) {
            return pieceName.getName().toUpperCase();
        }
        return pieceName.getName();
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
