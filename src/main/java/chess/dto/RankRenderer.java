package chess.dto;

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

    private final String command;
    private final Rank rank;

    RankRenderer(String command, Rank rank) {
        this.command = command;
        this.rank = rank;
    }

    static public Rank renderToRank(String input) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Rank입니다."))
                .rank;
    }
}
