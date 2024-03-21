package model.piece;

import java.util.Set;
import model.Camp;
import view.message.PieceType;
import model.position.Moving;
import model.position.Position;

public class Queen extends Piece {

    public Queen(final Camp camp) {
        super(camp);
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
        return PieceType.from(this).getValue();
    }
}
