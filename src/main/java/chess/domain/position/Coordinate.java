package chess.domain.position;

import chess.domain.piece.strategy.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Coordinate {

    private static final Map<String, Coordinate> CACHE = new HashMap<>();

    static {
        cache();
    }

    private final Column column;
    private final Row row;

    private Coordinate(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Coordinate ofName(final String positionName) {
        return CACHE.get(positionName);
    }

    public Coordinate move(final Direction direction) {
        final Column newColumn = column.move(direction);
        final Row newRow = row.move(direction);
        return CACHE.get(newColumn.value() + newRow.value());
    }

    public List<Coordinate> moveUntilWall(final Direction direction) {
        final List<Coordinate> visitedCoordinates = new ArrayList<>();
        Coordinate currentCoordinate = this;
        while(currentCoordinate.canMove(direction)) {
            currentCoordinate = currentCoordinate.move(direction);
            visitedCoordinates.add(currentCoordinate);
        }
        return visitedCoordinates;
    }

    public boolean canMove(Direction direction) {
        return column.canMove(direction) && row.canMove(direction);
    }

    public boolean isOfColumn(Column column) {
        return this.column.equals(column);
    }

    public String name() {
        return column.value() + row.value();
    }

    private static void cache() {
        for (Column column : Column.values()) {
            cacheRowsWithColumn(column);
        }
    }

    private static void cacheRowsWithColumn(final Column column) {
        for (Row row : Row.values()) {
            CACHE.put(column.value() + row.value(), new Coordinate(column, row));
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
        Coordinate that = (Coordinate) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
