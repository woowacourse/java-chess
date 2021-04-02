package domain.position;

import static domain.position.Position.CHESS_BOARD_END_RANGE;
import static domain.position.Position.CHESS_BOARD_START_RANGE;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Column implements Serializable {

    private static final List<String> chessColumns = Arrays
        .asList("a", "b", "c", "d", "e", "f", "g", "h");
    private final int value;

    public Column(int value) {
        this.value = value;
    }

    public Column(String chessCoordinate) {
        this(chessCoordinate.charAt(0) - 'a');
        validateChessCoordinate(chessCoordinate);
    }

    private void validateChessCoordinate(String chessCoordinate) {
        if (!chessColumns.contains(String.valueOf(chessCoordinate.charAt(0)))) {
            throw new IllegalArgumentException("[Error] 유효하지 않은 체스 좌표 입니다.");
        }
    }

    public String chessCoordinate() {
        return chessColumns.get(value);
    }

    public Column sum(Column column) {
        return new Column(value + column.value);
    }

    public Column difference(Column column) {
        return new Column(value - column.value);
    }

    public int abs(Column column) {
        return Math.abs(value - column.value);
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
        Column that = (Column) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
