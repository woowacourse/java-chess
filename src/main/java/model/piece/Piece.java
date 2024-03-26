package model.piece;

import java.util.Objects;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public abstract class Piece {

    private final Camp camp;
    private final String pieceName;
    private final double score;

    public Piece(Camp camp, String pieceName, double score) {
        this.camp = camp;
        this.pieceName = pieceName;
        this.score = score;
    }

    public abstract Set<Position> getMoveRoute(final Moving moving);

    protected abstract boolean canMovable(final Moving moving);

    public Set<Position> getAttackRoute(final Moving moving) {
        return getMoveRoute(moving);
    }

    public boolean isSameCamp(final Camp target) {
        return camp == target;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public Camp getCamp() {
        return camp;
    }

    public double getScore() {
        return score;
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
