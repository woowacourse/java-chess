package chess.domain.position;

import chess.domain.utils.RegexUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberRow {
    public static final NumberRow ERROR = new NumberRow(0);
    public static final int START_NUMBER = 1;
    public static final int INITIAL_CAPACITY = 8;
    private static final Map<Integer, NumberRow> cache = new LinkedHashMap<>(INITIAL_CAPACITY);

    static {
        for (int i = START_NUMBER; i < INITIAL_CAPACITY + START_NUMBER; i++) {
            cache.put(i, new NumberRow(i));
        }
    }

    private final int number;

    private NumberRow(int i) {
        this.number = i;
    }

    public static NumberRow valueOf(String value) {
        return valueOf(validNumber(value));
    }

    public static NumberRow valueOf(char value) {
        return valueOf(validNumber(value));
    }

    public static NumberRow valueOf(int value) {
        if (cache.containsKey(value)) {
            return cache.get(value);
        }
        return ERROR;
    }

    private static int validNumber(char value) {
        return validNumber(String.valueOf(value));
    }

    private static int validNumber(String value) {
        if (RegexUtils.isValidRowNumber(value)) {
            return Integer.parseInt(value);
        }
        throw new IllegalArgumentException("유효하지 않은 입력입니다. 숫자이어야 합니다.");
    }

    public String movedNumber(int value) {
        return NumberRow.valueOf(number + value).number();
    }

    public static List<NumberRow> values() {
        return new ArrayList<>(cache.values());
    }

    public String number() {
        return String.valueOf(number);
    }

    @Override
    public String toString() {
        return "" + number;
    }

}
