package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    private static final char MIN_COLUMN = 'a';
    private static final char MAX_COLUMN = 'h';
    private static final char MIN_ROW = '1';
    private static final char MAX_ROW = '8';

    private static final char WHITE_PAWN_INITIAL_ROW = '2';
    private static final char BLACK_PAWN_INiTIAL_ROW = '7';

    private static final int POSITION_FORMAT_LENGTH = 2;

    private static final List<Position> CACHE = createCache();

    private final char column;
    private final char row;

    private static List<Position> createCache() {
        List<Position> cache = new ArrayList<>();
        for (char column = MIN_COLUMN; column <= MAX_COLUMN; column++) {
            addPositionByColumn(cache, column);
        }
        return cache;
    }

    private static void addPositionByColumn(List<Position> cache, char column) {
        for (char row = MIN_ROW; row <= MAX_ROW; row++) {
            cache.add(new Position(column, row));
        }
    }

    private Position(char column, char row) {
        validateColumnInRange(column);
        validateRowInRange(row);
        this.column = column;
        this.row = row;
    }

    private void validateColumnInRange(char column) {
        if (!isColumnInRange(column)) {
            throw new IllegalArgumentException(
                    String.format("열 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_COLUMN, MAX_COLUMN));
        }
    }

    private boolean isColumnInRange(char column) {
        return MIN_COLUMN <= column && column <= MAX_COLUMN;
    }

    private void validateRowInRange(char row) {
        if (!isRowInRange(row)) {
            throw new IllegalArgumentException(
                    String.format("행 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_ROW, MAX_ROW));
        }
    }

    private boolean isRowInRange(char row) {
        return MIN_ROW <= row && row <= MAX_ROW;
    }

    public static Position of(char column, char row) {
        return CACHE.stream()
                .filter(position -> position.column == column && position.row == row)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Position범위에 맞지 않는 입력값입니다."));
    }

    public static Position from(String position) {
        if (position.length() != POSITION_FORMAT_LENGTH) {
            throw new IllegalArgumentException(String.format("Position format은 %d글자입니다.", POSITION_FORMAT_LENGTH));
        }
        return Position.of(position.charAt(0), position.charAt(1));
    }

    public boolean equalsColumnOrRow(Position position) {
        return this.column == position.column || this.row == position.row;
    }

    public boolean equalsColumn(Position position) {
        return this.column == position.column;
    }

    public int calculateDistance(Position position) {
        return Math.abs(this.column - position.column) + Math.abs(this.row - position.row);
    }

    public Position move(int columnAmount, int rowAmount) {
        return Position.of((char) (column + columnAmount), (char) (row + rowAmount));
    }

    public boolean isMovable(int columnAmount, int rowAmount) {
        return isColumnInRange((char) (column + columnAmount)) && isRowInRange((char) (row + rowAmount));
    }

    public boolean isPromotionPosition(Color color) {
        if (color.isWhite()) {
            return row == MAX_ROW;
        }
        return row == MIN_ROW;
    }

    public boolean isInitialPawnPosition(Color color) {
        if (color.isWhite()) {
            return row == WHITE_PAWN_INITIAL_ROW;
        }
        return row == BLACK_PAWN_INiTIAL_ROW;
    }

    public char column() {
        return column;
    }

    public char row() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
