package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String value;

    Rank(final String value) {
        this.value = value;
    }

    static Rank of(final String value) {
        return Arrays.stream(Rank.values())
                .filter((it) -> it.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    Rank add(final int gap) {
        return Rank.of(String.valueOf(Integer.parseInt(value) + gap));
    }

    int calculateDistance(final Rank target) {
        return Integer.parseInt(value) - Integer.parseInt(target.value);
    }

    String value() {
        return value;
    }
}
