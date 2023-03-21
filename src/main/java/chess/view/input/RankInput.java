package chess.view.input;

import chess.domain.position.Rank;
import java.util.Arrays;

public enum RankInput {
    EIGHT(Rank.EIGHT, "8"),
    SEVEN(Rank.SEVEN, "7"),
    SIX(Rank.SIX, "6"),
    FIVE(Rank.FIVE, "5"),
    FOUR(Rank.FOUR, "4"),
    THREE(Rank.THREE, "3"),
    TWO(Rank.TWO, "2"),
    ONE(Rank.ONE, "1"),
    ;

    private final Rank rank;
    private final String viewRank;

    RankInput(Rank rank, String viewRank) {
        this.rank = rank;
        this.viewRank = viewRank;
    }

    public static Rank toRank(String viewRank) {
        return Arrays.stream(RankInput.values())
                .filter(it -> it.viewRank.equals(viewRank))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랭크 입니다."))
                .rank;
    }
}
