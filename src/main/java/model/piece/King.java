package model.piece;

import model.Camp;
import model.position.Moving;
import model.position.Position;

import java.util.Set;

public class King extends Piece {

    public King(final Camp camp) {
        super(camp, new PieceName("k"));
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        return Set.of();
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        if (moving.isNotMoved()) {
            return false;
        }
        final int currentRow = currentPosition.getRowIndex();
        final int currentColumn = currentPosition.getColumnIndex();

        final int nextRow = nextPosition.getRowIndex();
        final int nextColumn = nextPosition.getColumnIndex();

        return Math.abs(nextRow - currentRow) <= 1 && Math.abs(nextColumn - currentColumn) <= 1;
    }

    @Override
    public String toString() {
        return getName();
    }
}
