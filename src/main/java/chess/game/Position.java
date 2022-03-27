package chess.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

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

    public int getColumnDistance(final Position to) {
        return this.column.getDistance(to.column);
    }

    public int getRowDistance(final Position to) {
        return this.row.getDistance(to.row);
    }

    public boolean isPawnInitial() {
        return row.isPawnRow();
    }

    public boolean isSameColumn(final Position other) {
        return column == other.column;
    }

    public Direction getDir(final Position other) {
        final List<Position> northPositions = getNorthPositions();
        if (northPositions.contains(other)) {
            return Direction.N;
        }
        final List<Position> southPositions = getSouthPositions();
        if (southPositions.contains(other)) {
            return Direction.S;
        }
        final List<Position> eastPositions = getEastPositions();
        if (eastPositions.contains(other)) {
            return Direction.E;
        }
        final List<Position> westPositions = getWestPositions();
        if (westPositions.contains(other)) {
            return Direction.W;
        }
        final List<Position> northEastPositions = getNorthEastPositions();
        if (northEastPositions.contains(other)) {
            return Direction.NE;
        }
        final List<Position> southEastPositions = getSouthEastPositions();
        if (southEastPositions.contains(other)) {
            return Direction.SE;
        }
        final List<Position> northWestPositions = getNorthWestPositions();
        if (northWestPositions.contains(other)) {
            return Direction.NW;
        }
        final List<Position> southWestPositions = getSouthWestPositions();
        if (southWestPositions.contains(other)) {
            return Direction.SW;
        }
        return null;
    }

    private List<Position> getNorthPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = row.getValue(); i <= 8; i++) {
            positions.add(Position.of(column, Row.of(i)));
        }
        return positions;
    }

    private List<Position> getSouthPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = row.getValue(); i >= 1; i--) {
            positions.add(Position.of(column, Row.of(i)));
        }
        return positions;
    }

    private List<Position> getEastPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(); i <= 8; i++) {
            positions.add(Position.of(Column.of(i), row));
        }
        return positions;
    }

    private List<Position> getWestPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(); i >= 1; i--) {
            positions.add(Position.of(Column.of(i), row));
        }
        return positions;
    }

    private List<Position> getNorthEastPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i <= 8 && j <= 8; i++, j++) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
    }

    private List<Position> getNorthWestPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i >= 1 && j <= 8; i--, j++) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
    }

    private List<Position> getSouthEastPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i <= 8 && j >= 1; i++, j--) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
    }

    private List<Position> getSouthWestPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = column.getValue(), j = row.getValue(); i >= 1 && j >= 1; i--, j--) {
            positions.add(Position.of(Column.of(i), Row.of(j)));
        }
        return positions;
    }

    public Position shift(final Direction direction) {
        final Column column = Column.of(this.column.getValue() + direction.getColumn());
        final Row row = Row.of(this.row.getValue() + direction.getRow());
        return Position.of(column, row);
    }

    public boolean equalsColumn(final int column) {
        return this.column.getValue() == column;
    }
}
