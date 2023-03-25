package chess.renderer;

import chess.domain.board.Rank;

import java.util.Arrays;

public enum RankRenderer {
    EIGHT("8", Rank.EIGHT),
    SEVEN("7", Rank.SEVEN),
    SIX("6", Rank.SIX),
    FIVE("5", Rank.FIVE),
    FOUR("4", Rank.FOUR),
    THREE("3", Rank.THREE),
    TWO("2", Rank.TWO),
    ONE("1", Rank.ONE);
    private final String string;
    private final Rank rank;

    RankRenderer(String string, Rank rank) {
        this.string = string;
        this.rank = rank;
    }

    static public Rank renderString(String input) {
        return Arrays.stream(values())
                .filter(value -> value.string.equals(input))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .rank;
    }

    static public String renderRank(Rank rank) {
        return Arrays.stream(values())
                .filter(value -> value.rank.equals(rank))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .string;
    }
}
