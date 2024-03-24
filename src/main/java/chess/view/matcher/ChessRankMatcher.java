package chess.view.matcher;

import chess.domain.position.ChessRank;

import java.util.Arrays;

public enum ChessRankMatcher {
    ONE("1", ChessRank.ONE),
    TWO("2", ChessRank.TWO),
    THREE("3", ChessRank.THREE),
    FOUR("4", ChessRank.FOUR),
    FIVE("5", ChessRank.FIVE),
    SIX("6", ChessRank.SIX),
    SEVEN("7", ChessRank.SEVEN),
    EIGHT("8", ChessRank.EIGHT),
    ;

    private final String text;
    private final ChessRank rank;

    ChessRankMatcher(String text, ChessRank rank) {
        this.text = text;
        this.rank = rank;
    }

    public static ChessRank matchByText(final String inputText) {
        return Arrays.stream(values())
                .filter(rankMatcher -> rankMatcher.text.equals(inputText))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 ChessRank를 찾을 수 없습니다."))
                .rank;
    }
}
