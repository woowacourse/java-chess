package chess.model.piece;

import chess.model.position.Distance;

public abstract class Piece {

    private final Camp camp;

    protected Piece(final Camp camp) {
        this.camp = camp;
    }

    public abstract Piece pick();

    public abstract boolean movable(final Distance distance, final Piece target);

    public abstract boolean isNotPassable();

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public boolean isSameTeam(final Camp camp) {
        return this.camp.isSameCamp(camp);
    }

    public final Camp camp() {
        return camp;
    }
}
