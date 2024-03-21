package model.piece;

import java.util.Set;
import model.Camp;
import model.PieceType;
import model.position.Moving;
import model.position.Position;

public class Rook extends Piece {

    public Rook(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getMoveRoute(Moving moving) {
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }

        return moving.route();
    }

    @Override
    public boolean canMovable(Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        return moving.isHorizontal() || moving.isVertical();
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
