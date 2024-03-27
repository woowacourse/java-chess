package view.mapper;

import domain.position.Rank;
import java.util.Arrays;

public enum RankInput {

    EIGHT(Rank.EIGHT, "8"),
    SEVEN(Rank.SEVEN, "7"),
    SIX(Rank.SIX, "6"),
    FIVE(Rank.FIVE, "5"),
    FOUR(Rank.FOUR, "4"),
    THREE(Rank.THREE, "3"),
    TWO(Rank.TWO, "2"),
    ONE(Rank.ONE, "1");

    private final Rank rank;
    private final String input;

    RankInput(Rank rank, String input) {
        this.rank = rank;
        this.input = input;
    }

    public static Rank asRank(String input) {
        return Arrays.stream(values())
                .filter(rankInput -> rankInput.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바른 위치를 입력해주세요."))
                .rank;
    }
}
