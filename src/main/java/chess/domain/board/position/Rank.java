package chess.domain.board.position;

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
    EIGHT(8);

    private static final char STARTING_CHARACTER_OF_RANK = '0';

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank from(int value) {
        return Arrays.stream(values())
                     .filter(it -> it.value == value)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 Rank 방향입니다."));
    }

    public static Rank from(char value) {
        return Arrays.stream(values())
                     .filter(it -> it.value == value - STARTING_CHARACTER_OF_RANK)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 Rank 방향입니다."));
    }

    public int differenceBetween(Rank other) {
        return value - other.value;
    }

    public int value() {
        return value;
    }
}
