package chess.view;

import chess.domain.position.Rank;

import java.util.Arrays;

public enum RankView {
    FIRST("1", Rank.FIRST),
    SECOND("2", Rank.SECOND),
    THIRD("3", Rank.THIRD),
    FOURTH("4", Rank.FOURTH),
    FIFTH("5", Rank.FIFTH),
    SIXTH("6", Rank.SIXTH),
    SEVENTH("7", Rank.SEVENTH),
    EIGHTH("8", Rank.EIGHTH);

    private final String text;
    private final Rank rank;

    RankView(String text, Rank rank) {
        this.text = text;
        this.rank = rank;
    }

    public static Rank from(String rankText) {
        return Arrays.stream(values())
                .filter(rankView -> rankText.equals(rankView.text))
                .map(rankView -> rankView.rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank는 1~8 중 하나이어야 합니다."));
    }
}
