package domain.position;

import static domain.position.Position.CHESS_BOARD_END_RANGE;
import static domain.position.Position.CHESS_BOARD_START_RANGE;

import java.util.Objects;

public class RowDegree {

    private final int value;

    public RowDegree(int value) {
        this.value = value;
    }

    public RowDegree(String chessCoordinate) {
        this((chessCoordinate.charAt(1) - '8') * -1);
    }

    public boolean isChessBoardPosition() {
        return value >= CHESS_BOARD_START_RANGE && value < CHESS_BOARD_END_RANGE;
    }

    public RowDegree sum(RowDegree rowDegree) {
        return new RowDegree(value + rowDegree.value);
    }

    public RowDegree difference(RowDegree rowDegree) {
        return new RowDegree(value - rowDegree.value);
    }

    public int abs(RowDegree rowDegree) {
        return Math.abs(value - rowDegree.value);
    }

    public boolean isGreaterThanZero() {
        return value > 0;
    }

    public boolean isLessThanZero() {
        return value < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RowDegree rowDegree = (RowDegree) o;
        return value == rowDegree.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
