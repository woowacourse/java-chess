package chess.domain.position;

import chess.domain.utils.RegexUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum NumberRows {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ERROR(0);

    private final int number;

    NumberRows(int value) {
        this.number = value;
    }

    public static NumberRows getInstance(int value) {
        return Arrays.stream(NumberRows.values())
            .filter(item -> item.number == value)
            .findFirst()
            .orElse(ERROR);
    }

    public static NumberRows getInstance(char value) {
        return getInstance(validNumber(value));
    }

    public static NumberRows getInstance(String value) {
        return getInstance(validNumber(value));
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
        return NumberRows.getInstance(number + value).number();
    }

    public String number() {
        return String.valueOf(number);
    }

    public static List<NumberRows> rows() {
        return Arrays.stream(NumberRows.values())
            .filter(number -> number != NumberRows.ERROR)
            .collect(Collectors.toList());
    }

}
