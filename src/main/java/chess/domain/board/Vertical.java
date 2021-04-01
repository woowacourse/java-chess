package chess.domain.board;

import java.util.Arrays;

public enum Vertical {
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    ONE("1", 1);

    private final String value;
    private final int weight;

    Vertical(final String value, final int weight) {
        this.value = value;
        this.weight = weight;
    }

    public static Vertical find(final String value) {
        return Arrays.stream(Vertical.values())
                .filter(vertical -> vertical.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("1~8 범위 밖의 값입니다."));
    }

    public static Vertical findFromWeight(int weight) {
        return Arrays.stream(Vertical.values())
                .filter(vertical -> vertical.weight == weight)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("1~8 범위 밖의 값입니다."));
    }

    public int getWeight() {
        return weight;
    }

    public String getValue() {
        return value;
    }
}
