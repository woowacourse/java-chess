package chess.domain.position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {

    public static final int ONE_SQUARE = 1;

    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public boolean isVerticalWay(Position other) {
        return this.column == other.column;
    }

    public boolean isHorizontalWay(Position other) {
        return this.row == other.row;
    }

    public boolean isDiagonalWay(Position other) {
        return getVerticalDistance(other) == getHorizontalDistance(other);
    }

    public boolean isAdjacent(Position other) {
        return (getVerticalDistance(other) | getHorizontalDistance(other)) == ONE_SQUARE;
    }

    public boolean isUpward(Position other) {
        return row.isUpward(other.row);
    }

    public boolean isDownward(Position other) {
        return row.isDownward(other.row);
    }

    public boolean isSameRow(Row row) {
        return this.row == row;
    }

    public int getVerticalDistance(Position other) {
        return row.getDistance(other.row);
    }

    public int getHorizontalDistance(Position other) {
        return column.getDistance(other.column);
    }

    public boolean hasLinearPath(Position to) {
        return isVerticalWay(to) || isHorizontalWay(to) || isDiagonalWay(to);
    }

    public Collection<Position> getLinearPath(Position to) {
        if (isVerticalWay(to)) {
            return getVerticalPath(to);
        }
        if (isHorizontalWay(to)) {
            return getHorizontalPath(to);
        }
        if (isDiagonalWay(to)) {
            return getDiagonalPath(to);
        }
        throw new IllegalArgumentException("일직선 상의 경로가 없습니다.");
    }

    private List<Position> getVerticalPath(Position to) {
        return row.getPath(to.row).stream()
            .map(row -> new Position(column, row))
            .collect(Collectors.toList());
    }

    private List<Position> getHorizontalPath(Position to) {
        return column.getPath(to.column).stream()
            .map(file -> new Position(file, row))
            .collect(Collectors.toList());
    }

    private Collection<Position> getDiagonalPath(Position to) {
        List<Row> rows = row.getPath(to.row);
        List<Column> columns = column.getPath(to.column);

        Collection<Position> result = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            result.add(new Position(columns.get(i), rows.get(i)));
        }
        return result;
    }

    public boolean isSameFile(Column column) {
        return this.column == column;
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
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return column.name() + row.getValue();
    }
}
