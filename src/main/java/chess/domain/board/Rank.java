package chess.domain.board;

import java.util.Arrays;

public enum Rank {
    EIGHT(7),
    SEVEN(6),
    SIX(5),
    FIVE(4),
    FOUR(3),
    THREE(2),
    TWO(1),
    ONE(0),
    EMPTY(-1);

    public static final char OFFSET_LETTER = '1';

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    public static Rank findRankByLetter(final char letter) {
        return findRank(letter - OFFSET_LETTER);
    }

    public static Rank findRank(final int rankIndex) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getValue() == rankIndex)
                .findFirst()
                .orElseThrow();
    }

    public boolean canMove(final Move move) {
        return 0 <= value + move.getY()
                && value + move.getY() <= 7;
    }

    public Rank moveRank(final Move move) {
        if (!canMove(move)) {
            throw new IllegalArgumentException("랭크를 움직일 수 없습니다.");
        }
        return findRank(value + move.getY());
    }

    public int getValue() {
        return value;
    }
}
