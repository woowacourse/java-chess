package chess;

import java.util.Arrays;

public enum Rank {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String value;
    private final int index;

    Rank(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    static public Rank from(final String value) {
        return Arrays.stream(Rank.values()).filter(rank -> rank.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank는 a에서 h사이의 값 이어야 합니다."));
    }

    int calculateDistance(final Rank rank) {
        return this.index - rank.index;
    }

}

