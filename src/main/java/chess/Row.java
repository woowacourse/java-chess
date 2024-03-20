package chess;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Row {
    private final int value;
    private static final Map<Integer, Row> CACHE = IntStream.rangeClosed(0, 7)
            .boxed()
            .collect(toMap(
                    i -> i,
                    Row::new
            ));

    private Row(int value) {
        this.value = value;
    }

    public static Row valueOf(String value) {
        validate(value);
        return CACHE.get(value.charAt(0) - 'a');
    }

    public Row update(int direction) {
        int rowDirection = direction;
        return CACHE.get(this.value + direction);
    }

    private static void validate(String value) {
        validateAlphabet(value);
        validateSize(value);
    }

    private static void validateAlphabet(String value) {
        //TODO : charAt 말고 다른 방법 생각해보기
        char row = value.charAt(0);
        if (row < 'a' || row > 'h') {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    private static void validateSize(String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("a~h까지 가능합니다.");
        }
    }

    public boolean isBigger(Row srcRow) {
        return this.value > srcRow.value;
    }

    public int getValue() {
        return value;
    }
}
