package model.piece;

import model.Camp;
import model.position.Moving;
import model.position.Position;

import java.util.Set;

public final class Bishop extends Piece {

    public Bishop(final Camp camp) {
        super(camp, new PieceName("b"));
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        return moving.route();
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        return moving.isDiagonal();
    }

    @Override
    public String toString() {
        return getName();
    }
}
