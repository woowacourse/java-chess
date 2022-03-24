package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

    private final String value;
    private final int index;

    Rank(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static Rank find(String value) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 체스판 범위를 벗어났습니다."));
    }

    public int getGap(Rank target) {
        return target.index - this.index;
    }

    public String getValue() {
        return value;
    }
}
