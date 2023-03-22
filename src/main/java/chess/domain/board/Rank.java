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
    ONE(0);

    public static final char DIFFERENCE_BETWEEN_LETTER_AND_INDEX = '1';

    private final int y;

    Rank(final int y) {
        this.y = y;
    }

    public static Rank findRankByLetter(final char letter) {
        return findRank(letter - DIFFERENCE_BETWEEN_LETTER_AND_INDEX);
    }

    public static Rank findRank(final int rankIndex) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getY() == rankIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 좌표를 찾을 수 없습니다."));
    }

    public int getY() {
        return this.y;
    }
}
