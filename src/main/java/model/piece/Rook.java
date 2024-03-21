package model.piece;

import java.util.HashSet;
import java.util.Set;
import model.Camp;
import model.PieceType;
import model.position.Column;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public class Rook extends Piece {

    private static final int[] dRow = new int[]{1, -1, 0, 0};
    private static final int[] dColumn = new int[]{0, 0, 1, -1};

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
        int index = findIndex(currentPosition, nextPosition);
        Set<Position> route = new HashSet<>();
        int d = Math.max(Math.abs(currentRow - nextRow), Math.abs(currentColumn - nextColumn));
        for (int i = 1; i < d; i++) {
            Row row = Row.from(currentRow + (i * dRow[index]));
            Column column = Column.from(currentColumn + (i * dColumn[index]));
            route.add(new Position(column, row));
        }
        return route;
    }

    private int findIndex(Position currentPosition, Position nextPosition) {
        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();

        if (nextRow - currentRow > 0) {
            return 0;
        }
        if (nextRow - currentRow < 0) {
            return 1;
        }
        if (nextColumn - currentColumn > 0) {
            return 2;
        }
        if (nextColumn - currentColumn < 0) {
            return 3;
        }
        throw new IllegalArgumentException("인덱스 없음");
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
