package chess.domain.position;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Row {
    private static final Map<Integer, Row> CACHE = IntStream.rangeClosed(0, 7)
            .boxed()
            .collect(toMap(
                    i -> i,
                    Row::new
            ));

    private final int value;

    private Row(int value) {
        this.value = value;
    }

    public static Row valueOf(String value) {
        validate(value);
        return CACHE.get(value.charAt(0) - 'a');
    }

    public Row update(int direction) {
        return CACHE.get(this.value + direction);
    }

    private static void validate(String value) {
        validateAlphabet(value);
        validateSize(value);
    }

    private static void validateAlphabet(String value) {
        char row = value.charAt(0);
        if (!CACHE.containsKey(row - 'a')) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    private static void validateSize(String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public int compare(Row row) {
        return Integer.compare(row.value, value);
    }
}
