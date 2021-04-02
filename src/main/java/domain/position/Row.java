package domain.position;

import static domain.position.Position.CHESS_BOARD_END_RANGE;
import static domain.position.Position.CHESS_BOARD_START_RANGE;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Row implements Serializable {

    private static final List<String> chessRows = Arrays
        .asList("8", "7", "6", "5", "4", "3", "2", "1");
    private final int value;

    public Row(int value) {
        this.value = value;
    }

    public Row(String chessCoordinate) {
        this((chessCoordinate.charAt(1) - '8') * -1);
        validateChessCoordinate(chessCoordinate);
    }

    public String chessCoordinate() {
        return chessRows.get(value);
    }

    private void validateChessCoordinate(String chessCoordinate) {
        if (!chessRows.contains(String.valueOf(chessCoordinate.charAt(1)))) {
            throw new IllegalArgumentException("[Error] 유효하지 않은 체스 좌표 입니다.");
        }
    }

    public boolean isChessBoardPosition() {
        return value >= CHESS_BOARD_START_RANGE && value < CHESS_BOARD_END_RANGE;
    }

    public Row sum(Row row) {
        return new Row(value + row.value);
    }

    public Row difference(Row row) {
        return new Row(value - row.value);
    }

    public int abs(Row row) {
        return Math.abs(value - row.value);
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
        Row row = (Row) o;
        return value == row.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
