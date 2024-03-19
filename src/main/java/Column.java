import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Column {
    private final int value;
    private static final Map<Integer, Column> CACHE = IntStream.rangeClosed(1, 8)
            .boxed()
            .collect(toMap(Function.identity(), Column::new));

    private Column (int value) {
        this.value = value;
    }

    public static Column valueOf(String value) {
        validate(value);
        return CACHE.get(Integer.parseInt(value));
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
}
