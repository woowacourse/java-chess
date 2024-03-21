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

public class Rook extends Piece {

    public Rook(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Moving moving) {
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }

        Position currentPosition = moving.currentPosition();
        Position nextPosition = moving.nextPosition();

        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();
        int index = moving.findIndex();
        Set<Position> route = new HashSet<>();
        int d = Math.max(Math.abs(currentRow - nextRow), Math.abs(currentColumn - nextColumn));
        for (int i = 1; i < d; i++) {
            Row row = Row.from(currentRow + (i * dRow[index]));
            Column column = Column.from(currentColumn + (i * dColumn[index]));
            route.add(new Position(column, row));
        }
        return route;
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
