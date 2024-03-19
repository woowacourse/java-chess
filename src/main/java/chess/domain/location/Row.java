package chess.domain.location;

import java.util.Arrays;

public enum Row {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String rowNumber;

    Row(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public static Row of(String input) {
        return Arrays.stream(values())
                .filter(row -> row.isNumber(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Row 입력입니다."));
    }

    //TODO 네이밍 고민
    private boolean isNumber(String input) {
        return this.rowNumber.equals(input);
    }
}
