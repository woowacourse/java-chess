package chess.domain.direction;

import chess.domain.Position;

import java.util.Arrays;

public enum BasicDirection implements Direction {
    NORTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    SOUTH(0, 1),
    NORTH_WEST(-1, -1),
    NORTH_EAST(1, -1),
    SOUTH_WEST(-1, 1),
    SOUTH_EAST(1, 1);

    private final int rowIndex;
    private final int columnIndex;

    BasicDirection(final int rowIndex, final int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public static Direction from(final Position source, final Position target) {
        final int rowDirection = source.findDirection(target.getRow());
        final int columnDirection = source.findDirection(target.getColumn());

        return Arrays.stream(BasicDirection.values())
                .filter(direction -> direction.isSame(rowDirection, columnDirection))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재할 수 없는 방향입니다."));
    }

    private boolean isSame(final int otherRowIndex, final int otherColumnIndex) {
        return this.rowIndex == otherRowIndex && this.columnIndex == otherColumnIndex;
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public int getColumnIndex() {
        return columnIndex;
    }
}
