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
    EIGHT("8")
    ;

    private final String notation;

    Rank(String notation) {
        this.notation = notation;
    }

    public static Rank of(String value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.notation.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크입니다."));
    }
}
