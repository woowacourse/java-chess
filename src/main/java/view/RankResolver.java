package view;

import domain.board.Rank;
import java.util.Arrays;

public enum RankResolver {

    ONE(new Rank(1), "1"),
    TWO(new Rank(2), "2"),
    THREE(new Rank(3), "3"),
    FOUR(new Rank(4), "4"),
    FIVE(new Rank(5), "5"),
    SIX(new Rank(6), "6"),
    SEVEN(new Rank(7), "7"),
    EIGHT(new Rank(8), "8"),
    ;

    private final Rank rank;
    private final String rankText;

    RankResolver(Rank rank, String rankText) {
        this.rank = rank;
        this.rankText = rankText;
    }

    public static Rank resolveRank(String text) {
        return Arrays.stream(values())
            .filter(value -> value.rankText.equals(text))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 rank입니다."))
            .rank;
    }
}
