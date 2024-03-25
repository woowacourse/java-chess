package model.piece;

import java.util.Objects;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public abstract class Piece {

    private final Camp camp;
    private final String pieceName;

    protected Piece(final Camp camp, final String pieceName) {
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
            return pieceName.toUpperCase();
        }
        return pieceName;
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
