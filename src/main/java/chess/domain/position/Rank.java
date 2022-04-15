package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    ONE("1", 7),
    TWO("2", 6),
    THREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0)
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

    public static Rank of(int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크입니다."));
    }

    public String getNotation() {
        return notation;
    }

    public int getIndex() {
        return index;
    }
}
