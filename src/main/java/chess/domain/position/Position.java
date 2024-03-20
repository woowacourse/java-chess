package chess.domain.position;

import static chess.domain.position.ColumnPosition.MAX_NUMBER;
import static chess.domain.position.ColumnPosition.MIN_NUMBER;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Position {
    private static final Map<String, Position> POOL = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
            .mapToObj(RowPosition::new)
            .flatMap(rowPosition -> IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                    .mapToObj(ColumnPosition::new)
                    .map(columnPosition -> new Position(rowPosition, columnPosition)))
            .collect(toMap(position -> Position.toKey(position.rowPosition, position.columnPosition),
                    position -> position));

    private final RowPosition rowPosition;
    private final ColumnPosition columnPosition;

    public Position(RowPosition rowPosition, ColumnPosition columnPosition) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
    }

    public static Position of(int rowPosition, int colPosition) {
        RowPosition row = new RowPosition(rowPosition);
        ColumnPosition col = new ColumnPosition(colPosition);
        return POOL.get(toKey(row, col));
    }

    private static String toKey(RowPosition rowPosition, ColumnPosition colPosition) {
        return String.valueOf(rowPosition) + String.valueOf(colPosition);
    }

    public Position verticalReversePosition() {
        return POOL.get(toKey(rowPosition.reverse(), this.columnPosition));
    }

    public boolean isStraightWith(Position target) {
        return rowPosition.equals(target.rowPosition) || columnPosition.equals(target.columnPosition);
    }

    public boolean isDiagonalWith(Position target) {
        int rowInterval = rowPosition.intervalWith(target.rowPosition);
        int colInterval = columnPosition.intervalWith(target.columnPosition);
        return rowInterval == colInterval;
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
        return Objects.equals(rowPosition, position.rowPosition) && Objects.equals(columnPosition,
                position.columnPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowPosition, columnPosition);
    }
}
