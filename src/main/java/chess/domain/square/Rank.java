package chess.domain.square;

import java.util.Arrays;

public enum Rank {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    ;

    private static final String INVALID_RANK = "유효하지 않은 랭크입니다. 숫자 1~8 사이로 입력해주세요.";

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    public static Rank from(final String rank) {
        return Arrays.stream(Rank.values())
                .filter(value -> (value.index + 1) == Integer.parseInt(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }

    public Rank move(final int rankMoveStep) {
        return indexOf(this.index + rankMoveStep);
    }

    private Rank indexOf(final int index) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }

    public int index() {
        return index;
    }
}
