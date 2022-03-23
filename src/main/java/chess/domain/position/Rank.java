package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7)
    ;

    private final String notation;
    private final int index;

    Rank(String notation, int index) {
        this.notation = notation;
        this.index = index;
    }

    public static Rank of(String value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.notation.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크입니다."));
    }

    public int getIndex() {
        return index;
    }
}
