package chess;

import java.util.Arrays;

public enum Rank {

    EIGHT(1, '8'),
    SEVEN(2, '7'),
    SIX(3, '6'),
    FIVE(4, '5'),
    FOUR(5, '4'),
    THREE(6, '3'),
    TWO(7, '2'),
    ONE(8, '1'),
    ;

    private final int value;
    private final char name;

    Rank(int value, char name) {
        this.value = value;
        this.name = name;
    }

    public Rank add(int row) {
        if (canAdd(row)) {
            return Rank.of(value + row);
        }
        throw new IllegalArgumentException("[ERROR] 보드판의 세로줄은 8부터 1 입니다.");
    }

    public boolean canAdd(int row) {
        return value + row >= EIGHT.value && value + row <= ONE.value;
    }

    public static Rank of(int otherValue) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == otherValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }

    public static Rank of(char name) {
        return Arrays.stream(values())
                .filter(rank -> rank.name == name)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }

    public int subtractFrom(final Rank otherRank) {
        return this.value - otherRank.value;
    }
}
