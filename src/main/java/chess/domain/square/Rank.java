package chess.domain.square;

import chess.domain.exception.InvalidSquareIndexException;
import chess.domain.exception.RankNotFoundException;

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
                .orElseThrow(RankNotFoundException::new);
    }

    public static Rank getRankByIndex(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.value == rank.getValueFrom(index))
                .findFirst()
                .orElseThrow(InvalidSquareIndexException::new);
    }

    private int getValueFrom(final int index) {
        return index + ONE.value;
    }

    public boolean isBiggerThan(final Rank rank) {
        return value > rank.value;
    }

    public boolean isLowerThan(final Rank rank) {
        return value < rank.value;
    }

    public boolean isDifferenceOne(final Rank rank) {
        return Math.abs(value - rank.value) == 1;
    }

    public Rank move(int difference) {
        try {
            return Rank.from((char) (value + difference));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Rank의 범위를 벗어났습니다");
        }
    }

    public char getValue() {
        return value;
    }

    public int getDifference(final Rank other) {
        return other.value - value;
    }
}
