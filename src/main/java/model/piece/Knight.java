package model.piece;

import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public class Knight extends Piece {

    public Knight(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Moving moving) {
        if (canMovable(moving)) {
            return Set.of();
        }
        throw new IllegalArgumentException("이동 불가");
    }

    @Override
    protected boolean canMovable(Moving moving) {
        Position currentPosition = moving.currentPosition();
        Position nextPosition = moving.nextPosition();

        if (moving.isNotMoved()) {
            return false;
        }
        int dRow = Math.abs(currentPosition.getRowIndex() - nextPosition.getRowIndex());
        int dColumn = Math.abs(currentPosition.getColumnIndex() - nextPosition.getColumnIndex());
        return dRow + dColumn == 3 && dRow != 0 && dColumn != 0;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "n";
        }
        return "N";
    }
}
