package chess.game;

import static chess.game.Direction.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    private static final int MIN_POSITION = 1;
    private static final int MAX_POSITION = 8;

    private static final List<Position> cachedPositions;

    static {
        cachedPositions = Arrays.stream(Column.values())
                .flatMap(column -> Arrays.stream(Row.values())
                        .map(row -> new Position(column, row)))
                .collect(Collectors.toList());
    }

    private Column column;
    private Row row;

    private Position(final Column column, final Row row) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final String column, final int row) {
        return cachedPositions.stream()
                .filter(cachedPositions -> cachedPositions.column == Column.of(column) && cachedPositions.row == Row.of(row))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 위치입니다."));
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

    public int getColumnDistance(final Position to) {
        return this.column.getDistance(to.column);
    }

    public int getRowDistance(final Position to) {
        return this.row.getDistance(to.row);
    }

    public boolean isPawnBeginningRow() {
        return row.isTwo() || row.isSeven();
    }

    public boolean isSameColumn(final Position other) {
        return column == other.column;
    }

    public Position shift(final Direction direction) {
        final Column column = Column.of(this.column.getValue() + direction.getColumn());
        final Row row = Row.of(this.row.getValue() + direction.getRow());
        return Position.of(column, row);
    }

    public boolean equalsColumn(final int column) {
        return this.column.getValue() == column;
    }

    public Direction findDirection(final Position other) {
        if (getNorthPositions().contains(other)) {
            return N;
        }
        if (getSouthPositions().contains(other)) {
            return S;
        }
        if (getEastPositions().contains(other)) {
            return E;
        }
        if (getWestPositions().contains(other)) {
            return W;
        }
        return findKnightDirection(other);
    }

    private Direction findKnightDirection(final Position other) {
        if (getNorthEastPositions().contains(other)) {
            return NE;
        }
        if (getSouthEastPositions().contains(other)) {
            return SE;
        }
        if (getNorthWestPositions().contains(other)) {
            return NW;
        }
        if (getSouthWestPositions().contains(other)) {
            return SW;
        }
        throw new IllegalArgumentException("이동이 불가능 합니다.");
    }

    private List<Position> getNorthPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = row.getValue(); i <= MAX_POSITION; i++) {
            positions.add(Position.of(column, Row.of(i)));
        }
        return positions;
    }

    private List<Position> getSouthPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = row.getValue(); i >= MIN_POSITION; i--) {
            positions.add(Position.of(column, Row.of(i)));
        }
        return positions;
    }

    private List<Position> getEastPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(); i <= MAX_POSITION; i++) {
            positions.add(Position.of(Column.of(i), row));
        }
        return positions;
    }

    private List<Position> getWestPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(); i >= MIN_POSITION; i--) {
            positions.add(Position.of(Column.of(i), row));
        }
        return positions;
    }

    private List<Position> getNorthEastPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i <= MAX_POSITION && j <= MAX_POSITION; i++, j++) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
    }

    private List<Position> getNorthWestPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i >= MIN_POSITION && j <= MAX_POSITION; i--, j++) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
    }

    private List<Position> getSouthEastPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i <= MAX_POSITION && j >= MIN_POSITION; i++, j--) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
    }

    private List<Position> getSouthWestPositions() {
        final List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i >= MIN_POSITION && j >= MIN_POSITION; i--, j--) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
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
