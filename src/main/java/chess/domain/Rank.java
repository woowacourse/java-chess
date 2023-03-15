package chess.domain;

import java.util.Arrays;

public enum Rank {

    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8');

    private final char value;

    Rank(final char value) {
        this.value = value;
    }

    public static Rank from(final char value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank입니다."));
    }

    public char getValue() {
        return value;
    }
}
