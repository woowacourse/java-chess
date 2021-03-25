package domain.position;

import static domain.position.Position.CHESS_BOARD_END_RANGE;
import static domain.position.Position.CHESS_BOARD_START_RANGE;

import java.util.Objects;

public class ColumnDegree {

    private final int value;

    public ColumnDegree(int value) {
        this.value = value;
    }

    public ColumnDegree(String chessCoordinate) {
        this(chessCoordinate.charAt(0) - 'a');
    }

    public ColumnDegree sum(ColumnDegree columnDegree) {
        return new ColumnDegree(value + columnDegree.value);
    }

    public ColumnDegree difference(ColumnDegree columnDegree) {
        return new ColumnDegree(value - columnDegree.value);
    }

    public int abs(ColumnDegree columnDegree){
        return Math.abs(value - columnDegree.value);
    }

    public boolean isGreaterThanZero() {
        return value > 0;
    }

    public boolean isLessThanZero() {
        return value < 0;
    }

    public boolean isChessBoardPosition() {
        return value >= CHESS_BOARD_START_RANGE && value < CHESS_BOARD_END_RANGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColumnDegree that = (ColumnDegree) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
