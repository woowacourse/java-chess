package chess.domain.board.coordinate;

import chess.domain.direction.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Coordinate {

    private static final Map<String, Coordinate> CACHE = new HashMap<>();
    private static final int BLACK_PAWN_START_ROW = 7;
    private static final int WHITE_PAWN_START_ROW = 2;

    private final Column column;
    private final Row row;

    private Coordinate(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Coordinate of(Column column, Row row) {
        String key = column.getName() + row.getValue();
        Coordinate coordinate = CACHE.get(key);
        if (Objects.isNull(coordinate)) {
            CACHE.put(key, new Coordinate(column, row));
        }
        return CACHE.get(key);
    }

    public static Coordinate of(String value) {
        Column column = Column.of(value.substring(0, 1));
        Row row = Row.of(value.substring(1, 2));
        return of(column, row);
    }

    public Coordinate next(Direction direction) {
        return of(column.move(direction.getColumnVector()), row.move(direction.getRowVector()));
    }

    public boolean isPawnStartRow() {
        return row.isSame(BLACK_PAWN_START_ROW) || row.isSame(WHITE_PAWN_START_ROW);
    }

    public int columnGap(Coordinate to) {
        return column.gap(to.column);
    }

    public int rowGap(Coordinate to) {
        return row.gap(to.row);
    }

    public Column getColumn() {
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
        Coordinate that = (Coordinate) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return column.toString() + row.toString();
    }
}
