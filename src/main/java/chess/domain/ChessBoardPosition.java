package chess.domain;

import java.util.Objects;

public class ChessBoardPosition {
    private static final String POSITION_OUT_OF_RANGE = "[ERROR] 체스판 범위를 벗어나는 위치가 입력되었습니다.";
    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 8;

    private final int column;
    private final int row;

    public ChessBoardPosition(int column, int row) {
        validateRange(column);
        validateRange(row);
        this.column = column;
        this.row = row;
    }

    public static ChessBoardPosition of(int column, int row) {
        return new ChessBoardPosition(column, row);
    }

    private void validateRange(int target) {
        if (target < MINIMUM || MAXIMUM < target) {
            throw new IllegalArgumentException(POSITION_OUT_OF_RANGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoardPosition that = (ChessBoardPosition) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
