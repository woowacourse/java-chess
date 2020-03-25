package chess.domain;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Rank of(String rankValue) {
        return Arrays.stream(Rank.values())
                .filter(x -> x.number == Integer.parseInt(rankValue))
                .findAny()
                .orElseThrow(IllegalAccessError::new);
    }

    public static Rank of(int number) {
        return Arrays.stream(Rank.values()).filter(x -> x.number == number).findAny().orElseThrow(IllegalArgumentException::new);
    }
}
