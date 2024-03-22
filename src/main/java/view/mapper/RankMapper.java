package view.mapper;

import domain.board.Rank;

import java.util.Arrays;

public enum RankMapper {

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
    private final String symbol;

    RankMapper(Rank rank, String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public static Rank from(String input) {
        return Arrays.stream(values())
                .filter(rank -> rank.symbol.equals(input))
                .findFirst()
                .map(it -> it.rank)
                .orElseThrow(IllegalArgumentException::new);
    }
}
