package chess.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int col;

    Rank(int col) {
        this.col = col;
    }

    public static Rank of(int col) {
        return Arrays.stream(values())
                .filter(rank -> rank.col == col)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치입니다"));
    }

    public static List<Rank> emptyBaseLine() {
        return List.of(SIX, FIVE, FOUR, THREE);
    }

    public static List<Rank> getRanksInBoardOrder() {
        List<Rank> ranks = Arrays.asList(values());
        Collections.reverse(ranks);
        return ranks;
    }

    public Rank add(int col) {
        return Rank.of(this.col + col);
    }

    public int calculateGap(Rank target) {
        return target.col - this.col;
    }

    public String getName() {
        return String.valueOf(col);
    }
}
