package chess.view;

import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Objects;

public enum RankView {
    FIRST("1", Rank.FIRST),
    SECOND("2", Rank.SECOND),
    THIRD("3", Rank.THIRD),
    FOURTH("4", Rank.FOURTH),
    FIFTH("5", Rank.FIFTH),
    SIXTH("6", Rank.SIXTH),
    SEVENTH("7", Rank.SEVENTH),
    EIGHTH("8", Rank.EIGHTH);

    private final String viewName;
    private final Rank rank;

    RankView(final String viewName, final Rank rank) {
        this.viewName = viewName;
        this.rank = rank;
    }

    public static Rank find(String viewName) {
        return Arrays.stream(RankView.values())
                .filter(rankView -> Objects.equals(rankView.viewName, viewName))
                .findAny()
                .map(rankView -> rankView.rank)
                .orElseThrow(() -> new IllegalArgumentException("입력 값이 Rank에 준비되어 있지 않습니다."));
    }
}
