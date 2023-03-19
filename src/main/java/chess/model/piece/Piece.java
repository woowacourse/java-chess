package chess.model.piece;

import chess.model.position.Distance;

public abstract class Piece {

    private final Camp camp;

    public Piece(final Camp camp) {
        this.camp = camp;
    }

    public boolean movable(final Distance distance, final Piece target) {
        if (isUnAttackAble(target)) {
            return false;
        }
        return movable(distance);
    }

    private boolean isUnAttackAble(final Piece target) {
        return target.camp.isSameCamp(this.camp);
    }

    public Piece pick() {
        return this;
    }

    public boolean isSameTeam(final Camp camp) {
        return this.camp == camp;
    }

    public boolean isNotPassable() {
        return true;
    }

    protected abstract boolean movable(final Distance distance);

    public final Camp camp() {
        return camp;
    }
}
