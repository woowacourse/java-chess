package model.position;

import java.util.Objects;

// 클래스로 변경
public record Moving(Position currentPosition, Position nextPosition) {

    // 하, 우, 상, 좌, 하우, 하좌, 상우, 상좌
    public static final int[] dRow = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    public static final int[] dColumn = new int[]{0, 1, 0, -1, 1, -1, 1, -1};

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

    public int findIndex() {
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
}
