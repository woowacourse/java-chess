package chess.model.position;

import java.util.Arrays;

public enum Rank {

    EIGHT(1, "8"),
    SEVEN(2, "7"),
    SIX(3, "6"),
    FIVE(4, "5"),
    FOUR(5, "4"),
    THREE(6, "3"),
    TWO(7, "2"),
    ONE(8, "1"),
    ;

    private final int value;
    private final String name;

    Rank(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public Rank add(final int row) {
        return Rank.of(value + row);
    }

    public int subtractFrom(final Rank otherRank) {
        return this.value - otherRank.value;
    }

    public String nameOfRank() {
        return name;
    }

    public static Rank of(final int otherValue) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == otherValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }
}
