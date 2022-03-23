package chess.model;

import java.util.List;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8)
    ;

    private final int col;

    Rank(int col) {
        this.col = col;
    }

    public static List<Rank> emptyBaseLine() {
        return List.of(THREE, FOUR, FIVE, SIX);
    }
}
