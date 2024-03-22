package model.piece;

import model.Camp;
import model.position.Moving;
import model.position.Position;

import java.util.Set;

public class Queen extends Piece {

    public Queen(final Camp camp) {
        super(camp, new PieceName("q"));
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
        return moving.isDiagonal() || moving.isVertical() || moving.isHorizontal();
    }

    @Override
    public String toString() {
        return getName();
    }
}
