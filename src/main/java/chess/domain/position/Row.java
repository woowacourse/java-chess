package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Row {
    private static final Map<String, Row> CACHE = IntStream.rangeClosed('a', 'h')
            .mapToObj(i -> String.valueOf((char) i))
            .collect(toMap(Function.identity(), Row::new));

    private final String value;

    private Row(String value) {
        this.value = value;
    }

    public static Row valueOf(String value) {
        validate(value);
        return CACHE.get(value);
    }

    private static void validate(String value) {
        validateAlphabet(value);
        validateSize(value);
    }

    private static void validateAlphabet(String value) {
        if (!CACHE.containsKey(value)) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    private static void validateSize(String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    public Row update(int direction) {
        System.out.println(toChar(value) + direction);
        return CACHE.get(String.valueOf((char) (toChar(value) + direction)));
    }

    public int subtractRow(Row row) {
        return toChar(value) - toChar(row.value);
    }

    public int findDirection(Row row) {
        return Integer.compare(toChar(row.value), toChar(value));
    }

    private int toChar(String value) {
        return value.charAt(0);
    }

    public int getValue() {
        return toChar(value) - 'a';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Row row = (Row) o;
        return value == row.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
