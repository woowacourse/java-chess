package piece;

import java.util.HashSet;
import java.util.Set;
import model.Camp;
import point.Column;
import point.Position;
import point.Row;

public class Queen extends Piece {

    private static final int[] dRow = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    private static final int[] dColumn = new int[]{0, 1, 0, -1, 1, -1, 1, -1};

    // 하, 우, 상, 좌, 하우, 하좌, 상우, 상좌


    public Queen(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Position currentPosition, Position nextPosition) {
        if (!canMovable(currentPosition, nextPosition)) {
            throw new IllegalArgumentException("이동 불가");
        }

        Set<Position> route = new HashSet<>();

        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();

        int index = findIndex(currentPosition, nextPosition);
        int d = Math.max(Math.abs(currentRow - nextRow), Math.abs(currentColumn - nextColumn));

        for (int i = 1; i < d; i++) {
            Row row = Row.from(currentRow + (i * dRow[index]));
            Column column = Column.from(currentColumn + (i * dColumn[index]));
            route.add(new Position(row, column));
        }
        return route;

    }

    // 하, 우, 상, 좌, 하우, 하좌, 상우, 상좌

    private int findIndex(Position currentPosition, Position nextPosition) {

        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();

        // 룩
        if (nextRow - currentRow > 0 && currentColumn == nextColumn) {
            return 0;
        }
        if (nextRow == currentRow && nextColumn - currentColumn > 0) {
            return 1;
        }
        if (nextRow - currentRow < 0 && currentColumn == nextColumn) {
            return 2;
        }
        if (nextRow == currentRow && nextColumn - currentColumn < 0) {
            return 3;
        }

        // 비숍

        if (currentRow < nextRow && currentColumn < nextColumn) {
            return 4;
        }
        if (currentRow < nextRow && currentColumn > nextColumn) {
            return 5;
        }
        if (currentRow > nextRow && currentColumn < nextColumn) {
            return 6;
        }
        if (currentRow > nextRow && currentColumn > nextColumn) {
            return 7;
        }

        throw new IllegalArgumentException("인덱스 없음");
    }


    @Override
    public boolean canMovable(Position currentPosition, Position nextPosition) {
        if (currentPosition.equals(nextPosition)) {
            return false;
        }

        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();

        //룩방향
        if (nextRow - currentRow == 0 || nextColumn - currentColumn == 0) {
            return true;
        }

        //비숍
        return Math.abs(currentRow - nextRow) == Math.abs(currentColumn - nextColumn);
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "q";
        }
        return "Q";
    }
}
