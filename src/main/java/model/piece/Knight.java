package model.piece;

import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public final class Knight extends Piece {

    private static final int KNIGHT_DELTA_DISTANCE = 3;

    public Knight(final Camp camp) {
        super(camp, PieceType.KNIGHT);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (canMovable(moving)) {
            return Set.of();
        }
        throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        if (moving.isNotMoved()) {
            return false;
        }
        final int dRow = Math.abs(nextPosition.getRowIndex() - currentPosition.getRowIndex());
        final int dColumn = Math.abs(nextPosition.getColumnIndex() - currentPosition.getColumnIndex());
        return dRow + dColumn == KNIGHT_DELTA_DISTANCE && dRow != 0 && dColumn != 0;
    }
}
