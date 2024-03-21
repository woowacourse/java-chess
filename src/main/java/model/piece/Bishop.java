package model.piece;

import static model.position.Moving.dColumn;
import static model.position.Moving.dRow;

import java.util.HashSet;
import java.util.Set;
import model.Camp;
import model.PieceType;
import model.position.Column;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public class Bishop extends Piece {

    public Bishop(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Moving moving) {

        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        Position currentPosition = moving.currentPosition();
        Position nextPosition = moving.nextPosition();

        Set<Position> route = new HashSet<>();
        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();
        int nextRow = nextPosition.getRowIndex();
        int d = Math.abs(currentRow - nextRow);
        int index = moving.findIndex();
        for (int i = 1; i < d; i++) {
            Row row = Row.from(currentRow + (i * dRow[index]));
            Column column = Column.from(currentColumn + (i * dColumn[index]));
            route.add(new Position(column, row));
        }
        return route;
    }

    @Override
    protected boolean canMovable(Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        return moving.isDiagonal();
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
