package chess.domain;

public enum Direction {
    //row, column
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);

    private final int rowDirection;

    public int getColumnDirection() {
        return columnDirection;
    }

    public int getRowDirection() {
        return rowDirection;
    }

    private final int columnDirection;

    Direction(int rowDirection, int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public static Direction findDirection(Position source, Position target) {
        Row srcRow = source.getRow();
        Column srcColumn = source.getColumn();
        Row trgRow = target.getRow();
        Column trgColumn = target.getColumn();
        boolean isRowBigger = trgRow.isBigger(srcRow);
        boolean isColumnBigger = trgColumn.isBigger(srcColumn);


        if (srcRow == trgRow && isColumnBigger) {
            return UP;
        }
        if (srcRow == trgRow && !isColumnBigger) {
            return DOWN;
        }
        if (!isRowBigger && srcColumn == trgColumn) {
            return LEFT;
        }
        if (isRowBigger && srcColumn == trgColumn) {
            return RIGHT;
        }
        if (!isRowBigger && isColumnBigger) {
            return UP_LEFT;
        }
        if (isRowBigger && isColumnBigger) {
            return UP_RIGHT;
        }
        if (!isRowBigger && !isColumnBigger) {
            return DOWN_LEFT;
        }
        return DOWN_RIGHT;
    }

    public Position move(Position source) {
        return source.move(rowDirection, columnDirection);
    }
}
