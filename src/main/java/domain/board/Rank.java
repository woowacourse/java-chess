package domain.board;

import java.util.Arrays;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private static final int BASE_INDEX = 8;

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank of(final String value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value() == parseNumber(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Rank값입니다."));
    }

    private static int parseNumber(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Rank는 정수만 입력할 수 있습니다.");
        }
    }

    public static Rank of(final int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getIndex() == index)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 인덱스입니다."));
    }

    public int value() {
        return value;
    }

    public int getIndex() {
        return BASE_INDEX - value;
    }
}
