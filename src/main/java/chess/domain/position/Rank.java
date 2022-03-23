package chess.domain.position;

import java.util.Arrays;

public enum Rank {

    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    private final String value;

    Rank(String value) {
        this.value = value;
    }

    public static Rank of(final String value) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.getValue().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Rank 값 입니다."));
    }

    public String getValue() {
        return value;
    }
}
