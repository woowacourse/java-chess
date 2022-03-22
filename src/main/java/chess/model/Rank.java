package chess.model;

import java.util.List;

public enum Rank {
    ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT
    ;

    public static List<Rank> emptyBaseLine() {
        return List.of(THREE, FOUR, FIVE, SIX);
    }
}
