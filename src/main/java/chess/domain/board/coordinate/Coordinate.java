package chess.domain.board.coordinate;

import chess.domain.direction.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Coordinate {

    private static final Map<String, Coordinate> CACHE = new HashMap<>();
    private static final int BLACK_PAWN_START_ROW = 7;
    private static final int WHITE_PAWN_START_ROW = 2;

    static {
        for (Column column : Column.values()) {
            cache(column);
        }
    }

    private static void cache(Column column) {
        for (Row row : Row.values()) {
            CACHE.put(column.getName() + row.getValue(), new Coordinate(column, row));
        }
    }

    private final Column column;
    private final Row row;

    private Coordinate(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Coordinate of(String value) {
        Coordinate coordinate = CACHE.get(value);
        if (Objects.isNull(coordinate)) {
            throw new IllegalArgumentException("잘못된 좌표가 존재합니다. (a1 ~ h8) 사이의 값만 입력해주세요.");
        }
        return coordinate;
    }

    public static Coordinate of(Column column, Row row) {
        String key = column.getName() + row.getValue();
        return of(key);
    }

    public Coordinate next(Direction direction) {
        return of(column.move(direction.getColumnVector()), row.move(direction.getRowVector()));
    }

    public boolean isPawnStartRow() {
        return row.getValue() == BLACK_PAWN_START_ROW || row.getValue() == WHITE_PAWN_START_ROW;
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
}
