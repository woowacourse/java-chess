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

    private final int y;

    Rank(final int y) {
        this.y = y;
    }

    public static Rank findRankByLetter(final char letter) {
        return findRank(letter - OFFSET_LETTER);
    }

    public static Rank findRank(final int rankIndex) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getRank() == rankIndex)
                .findFirst()
                .orElseThrow();
    }

    public boolean canUp() {
        return this.y + 1 <= 7;
    }

    public Rank upRank() {
        if (!canUp()) {
            throw new IllegalStateException("랭크가 더 이상 올라갈 수 없습니다.");
        }
        return findRank(y + 1);
    }

    public int getRank() {
        return y;
    }
}
