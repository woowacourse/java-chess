package chess.renderer;

import chess.domain.board.Rank;

import java.util.Arrays;

public enum RankInputRenderer {
    EIGHT("8", Rank.EIGHT),
    SEVEN("7", Rank.SEVEN),
    SIX("6", Rank.SIX),
    FIVE("5", Rank.FIVE),
    FOUR("4", Rank.FOUR),
    THREE("3", Rank.THREE),
    TWO("2", Rank.TWO),
    ONE("1", Rank.ONE);
    private final String input;
    private final Rank output;

    RankInputRenderer(String input, Rank output) {
        this.input = input;
        this.output = output;
    }

    static public Rank renderString(String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equals(input))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .output;
    }

    static public String renderRank(Rank rank) {
        return Arrays.stream(values())
                .filter(value -> value.output.equals(rank))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .input;
    }
}
