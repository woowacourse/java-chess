package chess.domain.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> cache = new HashMap<>(64);

    private static final int STANDARD_VALUE_LENGTH = 2;

    private final Column column;
    private final Row row;

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static Position of(final Column column, final Row row) {
        final String columnValue = column.name().toLowerCase(Locale.ROOT);
        final String value = columnValue + row.getValue();
        return cache.computeIfAbsent(value, ignored -> new Position(column, row));
    }

    public static Position of(final String value) {
        validatePosition(value);
        final Column column = Column.of(value.substring(0, 1));
        final Row row = Row.of(validateRow(value));
        return cache.computeIfAbsent(value.toLowerCase(Locale.ROOT), ignored -> new Position(column, row));
    }

    private static void validatePosition(final String value) {
        validateBlank(value);
        validateLength(value);
    }

    private static void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("공백 또는 빈 문자열이면 안됩니다.");
        }
    }

    private static void validateLength(final String value) {
        if (value.length() != STANDARD_VALUE_LENGTH) {
            throw new IllegalArgumentException("포지션은 두 글자입니다.");
        }
    }

    private static int validateRow(final String value) {
        final String row = value.substring(1, 2);
        try {
            return Integer.parseInt(row);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("행이름은 숫자여야합니다.");
        }
    }

    public Position toDirection(final Direction direction) {
        try {
            final Column movedColumn = column.move(direction.getColumnValue());
            final Row movedRow = row.move(direction.getRowValue());
            return Position.of(movedColumn, movedRow);
        } catch (IndexOutOfBoundsException exception) {
            return this;
        }
    }

    public double getAngle(final Position position) {
        final int columnValue = position.column.getValue() - this.column.getValue();
        final int rowValue = position.row.getValue() - this.row.getValue();
        return Math.atan2(rowValue, columnValue);
    }

    public static List<Position> getAllPositionsOfRow(final Row row) {
        final List<Position> pawnPositions = new ArrayList<>();
        for (final Column column : Column.values()) {
            pawnPositions.add(Position.of(column, row));
        }
        return pawnPositions;
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
        return column.toString() + row.toString();
    }
}
