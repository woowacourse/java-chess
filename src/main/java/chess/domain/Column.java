package chess.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Column {
    private final int value;
    private static final Map<Integer, Column> CACHE = IntStream.rangeClosed(0, 7)
            .boxed()
            .collect(toMap(Function.identity(), Column::new));

    private Column(int value) {
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
        if (value < 1 || value > 8) {
            throw new IllegalArgumentException("1~8까지 가능합니다.");
        }
    }

    public Column update(int direction) {
        int columnDirection = direction;
        return CACHE.get(this.value + columnDirection);
    }


    public boolean isPawnStartPosition(Team team) {
        if (team.isWhite()) {
            return value == 1;
        }
        return value == 6;
    }

    public int subtractColumn(Column column) {
        return this.value - column.value;
    }

    public int getValue() {
        return value;
    }

    public int compare(Column column) {
        if(value > column.value) {
            return -1;
        }
        if(value == column.value) {
            return 0;
        }
        return 1;
    }
}
