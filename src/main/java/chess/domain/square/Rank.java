package chess.domain.square;

import java.util.Arrays;

public enum Rank {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    public static final String INVALID_RANK = "유효하지 않는 랭크입니다.";

    public static Rank from(String rank) {
        return Arrays.stream(Rank.values())
                .filter(value -> (value.ordinal() + 1) == Integer.parseInt(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK));
    }
}
