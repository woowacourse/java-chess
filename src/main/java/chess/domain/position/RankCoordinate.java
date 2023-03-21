package chess.domain.position;

import static java.lang.Math.max;
import static java.lang.Math.min;

import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum RankCoordinate {
    EIGHT(8, RankType.SIDE_RANK, Color.BLACK),
    SEVEN(7, RankType.PAWN_RANK, Color.BLACK),
    SIX(6, RankType.EMPTY_RANK, Color.EMPTY),
    FIVE(5, RankType.EMPTY_RANK, Color.EMPTY),
    FOUR(4, RankType.EMPTY_RANK, Color.EMPTY),
    THREE(3, RankType.EMPTY_RANK, Color.EMPTY),
    TWO(2, RankType.PAWN_RANK, Color.WHITE),
    ONE(1, RankType.SIDE_RANK, Color.WHITE),
    ;

    private final int rowNumber;
    private final RankType rankType;
    private final Color initColor;

    RankCoordinate(int rowNumber, RankType rankType, Color initColor) {
        this.rowNumber = rowNumber;
        this.rankType = rankType;
        this.initColor = initColor;
    }

    public static RankCoordinate findBy(int rowNumber) {
        return Arrays.stream(values())
                .filter(it -> it.rowNumber == rowNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 행 번호를 입력해주세요."));
    }

    public List<RankCoordinate> betweenRanks(RankCoordinate other) {
        List<RankCoordinate> result = IntStream.range(min(rowNumber, other.rowNumber), max(rowNumber, other.rowNumber))
                .skip(1)
                .mapToObj(RankCoordinate::findBy)
                .collect(Collectors.toList());
        if (rowNumber > other.rowNumber) {
            Collections.reverse(result);
        }
        return result;
    }

    public int calculateGap(final RankCoordinate other) {
        return rowNumber - other.rowNumber;
    }

    public RankType getRankType() {
        return rankType;
    }

    public Color getInitColor() {
        return initColor;
    }
}
