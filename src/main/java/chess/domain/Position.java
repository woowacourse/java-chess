package chess.domain;

import chess.domain.piece.abstractPiece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Position {
    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;
    private static final int MIN_COLUMN = 1;
    private static final int MAX_COLUMN = 8;

    private static final Map<Integer, Position> positions;

    static {
        positions = new HashMap<>();
        for (int i = MIN_ROW; i <= MAX_ROW; i++) {
            for (int j = MIN_COLUMN; j <= MAX_COLUMN; j++) {
                positions.put(toKey(i, j), new Position(i, j));
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
        return positions.get(toKey(row, column));
    }

    private static int toKey(int row, int column) {
        return (row - MIN_ROW) * MAX_COLUMN + column;
    }

    private static void validateRange(int row, int column) {
        if (isRowOutOfRange(row)) {
            throw new IllegalStateException(
                    "가로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_ROW, MAX_ROW, row));
        }
        if (isColumnOutOfRange(column)) {
            throw new IllegalStateException(
                    "세로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_COLUMN, MAX_COLUMN, column));
        }
    }

    private static boolean isRowOutOfRange(int row) {
        return row < MIN_ROW || row > MAX_ROW;
    }

    private static boolean isColumnOutOfRange(int column) {
        return column < MIN_COLUMN || column > MAX_COLUMN;
    }

    public Position move(int rowDifference, int columnDifference) {
        return Position.of(row + rowDifference, column + columnDifference);
    }

    public Stream<Position> findAllMovablePosition(Piece piece) {
        return positions.values()
                .stream()
                .filter(position -> position != this)
                .filter(position -> piece.isMovable(new Movement(this, position)));
    }

    public Stream<Position> findAllMovablePosition(Piece piece, boolean isAttack) {
        return positions.values()
                .stream()
                .filter(position -> position != this)
                .filter(position -> piece.isMovable(new Movement(this, position), isAttack));
    }

    public int calculateRowDifference(Position targetPosition) {
        return targetPosition.row - row;
    }

    public int calculateColumnDifference(Position targetPosition) {
        return targetPosition.column - column;
    }

    public static int sameColumnPositionCount(Stream<Position> positions) {
        Map<Integer, Integer> map = positions.collect(Collectors.toMap(
                position -> position.column,
                position -> 1,
                Integer::sum
        ));
        return map.values()
                .stream()
                .filter(value -> value > 1)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
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
