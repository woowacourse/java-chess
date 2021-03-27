package chess.domain.position;

import chess.domain.board.Path;
import chess.domain.piece.strategy.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Position {

    private static final Map<String, Position> CACHE = new HashMap<>();

    static {
        cache();
    }

    private final Column column;
    private final Row row;

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position ofName(final String positionName) {
        return CACHE.get(positionName);
    }

    public Position move(final Direction direction) {
        final Column newColumn = column.move(direction);
        final Row newRow = row.move(direction);
        return CACHE.get(newColumn.value() + newRow.value());
    }

    public Path shortPath(final Direction direction) {
        if (canMove(direction)) {
            return new Path(Collections.singletonList(move(direction)));
        }
        return new Path(Collections.emptyList());
    }

    public Path longPath(final Direction direction) {
        final List<Position> visitedPositions = new ArrayList<>();
        Position currentPosition = this;
        while(currentPosition.canMove(direction)) {
            currentPosition = currentPosition.move(direction);
            visitedPositions.add(currentPosition);
        }
        return new Path(visitedPositions);
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
            CACHE.put(column.value() + row.value(), new Position(column, row));
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
        Position that = (Position) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
