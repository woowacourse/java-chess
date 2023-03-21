package chess.domain.direction;

import chess.domain.Position;

import java.util.Arrays;

public enum KnightDirection implements Direction {
    NORTH_NORTH_WEST(-1, -2),
    NORTH_NORTH_EAST(1, -2),
    WEST_WEST_NORTH(-2, -1),
    WEST_WEST_SOUTH(-2, 1),
    EAST_EAST_NORTH(2, -1),
    EAST_EAST_SOUTH(2, 1),
    SOUTH_SOUTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, 2);

    private final int rowIndex;
    private final int columnIndex;

    KnightDirection(final int rowIndex, final int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public static Direction from(final Position source, final Position target) {
        final int diffRow = target.diff(source.getRow());
        final int diffColumn = target.diff(source.getColumn());

        return Arrays.stream(KnightDirection.values())
                .filter(direction -> direction.isSame(diffRow, diffColumn))
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
