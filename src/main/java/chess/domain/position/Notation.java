package chess.domain.position;

import chess.domain.piece.strategy.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Notation {

    private static final Map<String, Notation> CACHE = new HashMap<>();

    static {
        cache();
    }

    private final Column column;
    private final Row row;

    private Notation(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Notation ofName(final String positionName) {
        return CACHE.get(positionName);
    }

    public Notation move(final Direction direction) {
        final Column newColumn = column.move(direction);
        final Row newRow = row.move(direction);
        return CACHE.get(newColumn.value() + newRow.value());
    }

    public Notations shortRange(final Direction direction) {
        if (canMove(direction)) {
            return new Notations(Collections.singletonList(move(direction)));
        }
        return new Notations(Collections.emptyList());
    }

    public Notations longRange(final Direction direction) {
        final List<Notation> visitedNotations = new ArrayList<>();
        Notation currentNotation = this;
        while(currentNotation.canMove(direction)) {
            currentNotation = currentNotation.move(direction);
            visitedNotations.add(currentNotation);
        }
        return new Notations(visitedNotations);
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
            CACHE.put(column.value() + row.value(), new Notation(column, row));
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
        Notation that = (Notation) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
