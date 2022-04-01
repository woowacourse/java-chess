package chess.game;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private static final List<Position> cachedPositions;

    private static final int NOT_MOVE = 0;
    private static final int TWO_DISTANCE= 2;

    static {
        cachedPositions = Arrays.stream(Column.values())
                .flatMap(column -> Arrays.stream(Row.values())
                        .map(row -> new Position(column, row)))
                .collect(Collectors.toList());
    }

    private final Column column;
    private final Row row;

    private Position(final Column column, final Row row) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final Column column, final Row row) {
        return cachedPositions.stream()
                .filter(cachedPositions -> cachedPositions.column == column && cachedPositions.row == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다."));
    }

    public static Position of(final String position) {
        return cachedPositions.stream()
                .filter(cachedPositions -> isExist(position, cachedPositions))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다."));
    }

    private static boolean isExist(final String position, final Position cachedPositions) {
        return cachedPositions.column == Column.of(position.substring(0, 1)) &&
                cachedPositions.row == Row.of(position.substring(1, 2));
    }

    public Position shift(final Direction direction) {
        final Column column = Column.of(this.column.getValue() + direction.getColumn());
        final Row row = Row.of(this.row.getValue() + direction.getRow());
        return Position.of(column, row);
    }

    public int getColumnDistance(final Position to) {
        return this.column.distance(to.column);
    }

    public int getRowDistance(final Position to) {
        return this.row.distance(to.row);
    }

    public boolean isPawnBeginningRow() {
        return row.isTwo() || row.isSeven();
    }

    public boolean isSameColumn(final Position other) {
        return column == other.column;
    }

    public boolean isTwoPointDistance(final Position to) {
        return Math.abs(row.distance(to.row)) == TWO_DISTANCE;
    }

    public boolean equalsColumn(final int column) {
        return this.column.getValue() == column;
    }

    public boolean isNotColumnMove(final Position to) {
        return this.column.distance(to.column) == NOT_MOVE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        final Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
