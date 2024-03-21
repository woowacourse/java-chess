package model.position;

import static model.position.Direction.dColumn;
import static model.position.Direction.dRow;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// 클래스로 변경
public record Moving(Position currentPosition, Position nextPosition) {

    public boolean isHorizontal() {
        int currentRow = currentPosition.getRowIndex();
        int nextRow = nextPosition.getRowIndex();

        return currentRow == nextRow;
    }

    public boolean isVertical() {
        int currentColumn = currentPosition.getColumnIndex();
        int nextColumn = nextPosition.getColumnIndex();

        return currentColumn == nextColumn;
    }

    public boolean isDiagonal() {
        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();

        return Math.abs(currentRow - nextRow) == Math.abs(currentColumn - nextColumn);
    }

    public boolean isNotMoved() {
        return Objects.equals(currentPosition, nextPosition);
    }

    public Set<Position> route() {
        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();
        int index = Direction.from(currentPosition, nextPosition).getIndex();

        Set<Position> result = new HashSet<>();
        for (int i = 1; i < distance(); i++) {
            Row row = Row.from(currentRow + (i * dRow[index]));
            Column column = Column.from(currentColumn + (i * dColumn[index]));
            result.add(new Position(column, row));
        }
        return result;
    }

    private int distance() {
        int currentRow = currentPosition.getRowIndex();
        int currentColumn = currentPosition.getColumnIndex();

        int nextRow = nextPosition.getRowIndex();
        int nextColumn = nextPosition.getColumnIndex();
        return Math.max(Math.abs(currentRow - nextRow), Math.abs(currentColumn - nextColumn));
    }
}
