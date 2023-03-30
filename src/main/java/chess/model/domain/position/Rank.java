package chess.model.domain.position;

import java.util.Map;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private static final Map<Integer, Rank> RANK_MAPPER = Map.ofEntries(
            Map.entry(ONE.value, ONE), Map.entry(TWO.value, TWO), Map.entry(THREE.value, THREE),
            Map.entry(FOUR.value, FOUR), Map.entry(FIVE.value, FIVE), Map.entry(SIX.value, SIX),
            Map.entry(SEVEN.value, SEVEN), Map.entry(EIGHT.value, EIGHT)
    );
    static final String RANK_NOT_FOUND_MESSAGE = "일치하는 Rank를 찾을 수 없습니다.";

    private final int value;

    Rank(final int value) {
        this.value = value;
    }

    static Rank from(int value) {
        if (!RANK_MAPPER.containsKey(value)) {
            throw new IllegalArgumentException(RANK_NOT_FOUND_MESSAGE);
        }
        return RANK_MAPPER.get(value);
    }

    public int value() {
        return value;
    }

    public int calculateRankGap(final Rank subtrahend) {
        return value - subtrahend.value;
    }

    public Rank addValue(final int addition) {
        return from(value + addition);
    }
}
