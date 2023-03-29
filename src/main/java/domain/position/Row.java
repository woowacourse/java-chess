package domain.position;

import java.util.Map;

public final class Row {

    private final Map<Integer, Position> columns;

    public Row(final Map<Integer, Position> columns) {
        this.columns = columns;
    }

    public Position getPosition(final int column) {
        return columns.get(column);
    }

    public void putIfAbsent(final int column, final Position position) {
        columns.putIfAbsent(column, position);
    }
}
