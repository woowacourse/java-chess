package chess;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Column {
    private final int value;
    private static final Map<Integer, Column> CACHE = IntStream.rangeClosed(0, 7)
            .boxed()
            .collect(toMap(Function.identity(), Column::new));

    private Column (int value) {
        this.value = value;
    }

    public static Column valueOf(String value) {
        validate(value);
        return CACHE.get(Integer.parseInt(value) - 1);
    }

    private static void validate(String value) {
        try {
            int number = Integer.parseInt(value);
            validateInRange(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    private static void validateInRange(int value) {
        if(value < 1 || value > 8) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    public boolean isBigger(Column srcColumn) {
        return this.value > srcColumn.value;
    }

    public int getValue() {
        return value;
    }
}
