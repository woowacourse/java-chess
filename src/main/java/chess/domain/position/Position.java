package chess.domain.position;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final int POSITION_STRING_SIZE = 2;

    private static final int CACHE_SIZE = 64;
    private static final Map<String, Position> CACHE = new HashMap<>(CACHE_SIZE);

    private static final int COLUMN_START_INDEX = 0;
    private static final int COLUMN_END_INDEX = 1;
    private static final int ROW_START_INDEX = 1;
    private static final int ROW_END_INDEX = 2;

    static {
        for (Column column : Column.values()) {
            addCacheInColumn(column);
        }
    }

    private static void addCacheInColumn(Column column) {
        for (Row row : Row.values()) {
            String key = getKeyNameByColumnAndRow(column, row);
            CACHE.put(key, new Position(column, row));
        }
    }

    private static String getKeyNameByColumnAndRow(Column column, Row row) {
        return column.name().toLowerCase(Locale.ROOT) + row.getValue();
    }

    private final Column column;
    private final Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        String key = getKeyNameByColumnAndRow(column, row);
        return CACHE.get(key);
    }

    public static Position of(String value) {
        validateBlank(value);
        validateLength(value);
        Column column = Column.of(getColumnString(value));
        Row row = Row.of(parseRowStringToInt(value));
        return CACHE.computeIfAbsent(value.toLowerCase(Locale.ROOT), ignored -> new Position(column, row));
    }

    private static int parseRowStringToInt(String value) {
        try {
            return Integer.parseInt(getRowString(value));
        } catch(NumberFormatException e){
            throw new IllegalArgumentException("행은 숫자가 와야합니다.");
        }
    }

    private static String getColumnString(String value) {
        return value.substring(COLUMN_START_INDEX, COLUMN_END_INDEX);
    }

    private static String getRowString(String value) {
        return value.substring(ROW_START_INDEX, ROW_END_INDEX);
    }

    private static void validateBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("공백 또는 빈 문자열이면 안됩니다.");
        }
    }

    private static void validateLength(String value) {
        if (value.length() != POSITION_STRING_SIZE) {
            throw new IllegalArgumentException("포지션은 두 글자입니다.");
        }
    }

    public Position toDirection(Direction direction) {
        try {
            Column movedColumn = column.move(direction.getColumnValue());
            Row movedRow = row.move(direction.getRowValue());
            return Position.of(movedColumn, movedRow);
        } catch (IndexOutOfBoundsException exception) {
            return this;
        }
    }

    public String convertToString() {
        return column.name().toLowerCase() + row.getValue();
    }

    public int getColumnDifferenceWithTarget(Position targetPosition) {
        return Column.calculateDifference(targetPosition.column, this.column);
    }

    public int getRowDifferenceWithTarget(Position targetPosition) {
        return Row.calculateDifference(targetPosition.row, this.row);
    }

    public boolean isInRow(Row row) {
        return this.row == row;
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
