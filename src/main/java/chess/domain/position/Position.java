package chess.domain.position;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> cache = new HashMap<>(64);

    private static final int STANDARD_VALUE_LENGTH = 2;

    private final Column column;
    private final Row row;


    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(Column column, Row row) {
        String columnValue = column.name();
        String value = columnValue.toLowerCase(Locale.ROOT) + row.getValue();
        return cache.computeIfAbsent(value, ignored -> new Position(column, row));
    }

    public static Position of(String value) {
        validatePosition(value);
        Column column = Column.of(value.substring(0, 1));
        Row row = Row.of(Integer.parseInt(value.substring(1, 2)));
        return cache.computeIfAbsent(value.toLowerCase(Locale.ROOT), ignored -> new Position(column, row));
    }

    private static void validatePosition(String value) {
        validateBlank(value);
        validateLength(value);
    }

    private static void validateBlank(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("공백 또는 빈 문자열이면 안됩니다.");
        }
    }

    private static void validateLength(String value) {
        if (value.length() != STANDARD_VALUE_LENGTH) {
            throw new IllegalArgumentException("포지션은 두 글자입니다.");
        }
    }

    public Position toDirection(Direction direction) {
        try {
            Column movedColumn = column.move(direction.getColumnValue());
            Row movedRow = row.move(direction.getRowValue());
            return new Position(movedColumn, movedRow);
        } catch (IndexOutOfBoundsException exception) {
            return this;
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
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
