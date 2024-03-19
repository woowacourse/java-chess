package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;
    private static final int MIN_COLUMN = 1;
    private static final int MAX_COLUMN = 8;

    private static final List<Position> positions;

    static {
        positions = new ArrayList<>();
        for (int i = MIN_COLUMN; i <= MAX_COLUMN; i++) {
            for (int j = MIN_ROW; j <= MAX_ROW; j++) {
                positions.add(new Position(j, i));
            }
        }
    }

    private final int row;
    private final int column;

    private Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(int row, int column) {
        validateRange(row, column);
        return positions.stream()
                .filter(position -> position.row == row && position.column == column)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("캐시에 해당 값이 존재하지 않습니다."));
    }

    private static void validateRange(int row, int column) {
        if (row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException(
                    "가로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_ROW, MAX_ROW, row));
        }
        if (column < MIN_COLUMN || column > MAX_COLUMN) {
            throw new IllegalArgumentException(
                    "세로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_COLUMN, MAX_COLUMN, column));
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
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
