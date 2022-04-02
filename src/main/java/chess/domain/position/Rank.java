package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String value;

    Rank(String value) {
        this.value = value;
    }

    public static Rank of(String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 범위입니다."));
    }

    public Rank add(int gap) {
        return Rank.of(String.valueOf((char) (value.charAt(0) + gap)));
    }

    public int calculateDistance(Rank target) {
        return value.charAt(0) - target.value.charAt(0);
    }

    public String getValue() {
        return value;
    }
}
