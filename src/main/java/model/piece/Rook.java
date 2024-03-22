package model.piece;

import model.Camp;
import model.position.Moving;
import model.position.Position;

import java.util.Set;

public final class Rook extends Piece {

    public Rook(final Camp camp) {
        super(camp, new PieceName("r"));
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        return moving.route();
    }

    @Override
    public boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        return moving.isHorizontal() || moving.isVertical();
    }

    @Override
    public String toString() {
        return getName();
    }
}
