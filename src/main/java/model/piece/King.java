package model.piece;

import java.util.Set;
import model.Camp;
import model.PieceType;
import model.position.Moving;
import model.position.Position;

public class King extends Piece {

    public King(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Moving moving) {
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        return Set.of();
    }

    @Override
    protected boolean canMovable(Moving moving) {
        Position currentPosition = moving.currentPosition();
        Position nextPosition = moving.nextPosition();

        if (moving.isNotMoved()) {
            return false;
        }
        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();

        return Math.abs(nextRow - currentRow) <= 1 && Math.abs(nextColumn - currentColumn) <= 1;
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
