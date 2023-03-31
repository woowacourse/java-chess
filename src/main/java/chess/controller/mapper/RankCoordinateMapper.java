package chess.controller.mapper;

import chess.domain.board.RankCoordinate;

import java.util.Arrays;

import static chess.domain.board.RankCoordinate.INVALID_RANK_COORDINATE_MESSAGE;

public enum RankCoordinateMapper {
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

    RankCoordinateMapper(String rowView, RankCoordinate rankCoordinate) {
        this.rowView = rowView;
        this.rankCoordinate = rankCoordinate;
    }

    public static RankCoordinate findBy(String rowView) {
        return Arrays.stream(values())
                .filter(it -> it.rowView.equals(rowView))
                .map(it -> it.rankCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_COORDINATE_MESSAGE));
    }

    public static RankCoordinate findBy(int rowNumber) {
        return Arrays.stream(values())
                .map(it -> it.rankCoordinate)
                .filter(it -> it.getRowNumber() == rowNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_COORDINATE_MESSAGE));
    }
}
