package chess.domain.piece.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    OUT(-1)
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(int value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.value == value)
            .findFirst()
            .orElseThrow(NoSuchElementException::new);
    }

    public static Rank of(String value) {
        return Rank.of(Integer.parseInt(value));
    }

    public Rank getNext(int next) {
        if (value + next < ONE.value || value + next > EIGHT.value) {
            return OUT;
        }

        return Rank.of(value + next);
    }

    public int getValue() {
        return value;
    }
}
