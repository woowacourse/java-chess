package chess;

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

    Rank(final String value) {
        this.value = value;
    }

    static public Rank from(final String value) {
        return Arrays.stream(Rank.values()).filter(rank -> rank.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank는 a에서 h사이의 값 이어야 합니다."));
    }

}

