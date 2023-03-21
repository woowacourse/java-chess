package chess.view;

import chess.domain.position.RankCoordinate;
import java.util.Arrays;

public enum RankCoordinateView {
    EIGHT("8", RankCoordinate.EIGHT),
    SEVEN("7", RankCoordinate.SEVEN),
    SIX("6", RankCoordinate.SIX),
    FIVE("5", RankCoordinate.FIVE),
    FOUR("4", RankCoordinate.FOUR),
    THREE("3", RankCoordinate.THREE),
    TWO("2", RankCoordinate.TWO),
    ONE("1", RankCoordinate.ONE),
    ;

    private final String rowView;
    private final RankCoordinate rankCoordinate;

    RankCoordinateView(String rowView, RankCoordinate rankCoordinate) {
        this.rowView = rowView;
        this.rankCoordinate = rankCoordinate;
    }

    public static RankCoordinate findBy(String rowView) {
        return Arrays.stream(values())
                .filter(it -> it.rowView.equals(rowView))
                .map(it -> it.rankCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 열 번호를 입력해주세요."));
    }
}
