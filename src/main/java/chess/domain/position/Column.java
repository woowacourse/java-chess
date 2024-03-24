package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Column {
    private static final Map<String, Column> CACHE = IntStream.rangeClosed(1, 8)
            .mapToObj(String::valueOf)
            .collect(toMap(Function.identity(), Column::new));

    private final String value;

    private Column(String value) {
        this.value = value;
    }

    public static Column valueOf(String value) {
        validate(value);
        return CACHE.get(value);
    }

    private static void validate(String value) {
        try {
            Integer.parseInt(value);
            validateInRange(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    private static void validateInRange(String value) {
        if(!CACHE.containsKey(value)) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    public Column update(int value) {
        int index = Integer.parseInt(this.value) + value;
        return CACHE.get(String.valueOf(index));
    }

    public int subtractColumn(Column column) {
        return Integer.parseInt(this.value) - Integer.parseInt(column.value);
    }

    public int findDirection(Column column) {
        return Integer.compare(Integer.parseInt(column.value), Integer.parseInt(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Column column = (Column) o;
        return value == column.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
