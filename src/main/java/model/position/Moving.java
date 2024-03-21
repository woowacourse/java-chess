package model.position;

import java.util.Objects;

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
}
