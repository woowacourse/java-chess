package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final int COMPACT_VALUE_ONE = 1;
    private static final int COMPACT_VALUE_ZERO = 0;
    private static final int COMPACT_VALUE_MINUS_ONE = -1;
    private static final int INPUT_LENGTH = 2;
    private static final int COLUMN_START_INDEX = 0;
    private static final int ROW_START_INDEX = 1;
    private static final int ROW_END_INDEX = 2;
    private static final Map<String, Position> CACHE = new HashMap<>();

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        String key = madeKey(column, row);

        if (CACHE.get(key) == null) {
            CACHE.put(key, new Position(column, row));
        }
        return CACHE.get(key);
    }

    private static String madeKey(Column column, Row row) {
        return column.getValue() + row.getValue();
    }

    public static Position of(String value) {
        validInput(value);
        return of(Column.of(value.substring(COLUMN_START_INDEX, ROW_START_INDEX))
                , Row.of(value.substring(ROW_START_INDEX, ROW_END_INDEX)));
    }

    private static void validInput(String value) {
        if (value.length() != INPUT_LENGTH) {
            throw new IllegalArgumentException("올바른 값으로 Position 을 생성해주세요.");
        }
    }

    public String getName() {
        return column.getValue() + row.getValue();
    }

    public Direction findDirection(Position position) {
        int x = position.column.calculateDifference(column);
        int y = position.row.calculateDifference(row);

        return Direction.of(x, y);
    }

    public Direction findDirectionByCompactValue(Position position) {
        int x = position.column.calculateDifference(column);
        int y = position.row.calculateDifference(row);

        int nx = convertCompactValue(x, y);
        int ny = convertCompactValue(y, x);

        return Direction.of(nx, ny);
    }

    /**
     * target 으로 주어진 파라미터를 -1, 0, 1로 변환하기 위한 메서드. 단, 두 파라미터를 같은 값으로 나눴을 때 둘 다 -1, 0, 1으로 변환되어야 한다. 위 조건에 충족하지 않는다면 본래 값을
     * return 한다.
     *
     * @param target 반환할 값
     * @param other  compact value 생성 시 도와주는 값
     */
    private int convertCompactValue(int target, int other) {
        if (target == 0) {
            return 0;
        }

        int absoluteValue = Math.abs(target);
        if (checkNumber((float) other / absoluteValue)) {
            return target / absoluteValue;
        }
        return target;
    }

    private boolean checkNumber(float target) {
        return target == COMPACT_VALUE_MINUS_ONE
                || target == COMPACT_VALUE_ZERO
                || target == COMPACT_VALUE_ONE;
    }

    public boolean isSameRow(Row row) {
        return this.row == row;
    }

    public boolean isSameColumn(Position position) {
        return this.column == position.column;
    }

    public boolean movable(Direction direction) {
        return column.movable(direction.getColumn()) && row.movable(direction.getRow());
    }

    public Position move(Direction direction) {
        return of(column.move(direction.getColumn()), row.move(direction.getRow()));
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
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
