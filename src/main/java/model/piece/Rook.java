package model.piece;

import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public final class Rook extends Piece {

    public Rook(final Camp camp) {
        super(camp, PieceType.ROOK);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
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
}
