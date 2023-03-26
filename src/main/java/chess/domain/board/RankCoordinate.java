package chess.domain.board;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum RankCoordinate {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    public static final String INVALID_RANK_COORDINATE_MESSAGE = "올바른 행 번호를 입력해주세요.";
    private final int rowNumber;

    RankCoordinate(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public static RankCoordinate findBy(int rowNumber) {
        return Arrays.stream(values())
                .filter(it -> it.rowNumber == rowNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_RANK_COORDINATE_MESSAGE));
    }

    public static List<RankCoordinate> getSortedRankCoordinates() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(RankCoordinate::getRowNumber).reversed())
                .collect(Collectors.toList());
    }

    public int compare(RankCoordinate other) {
        return Integer.compare(other.rowNumber, this.rowNumber);
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int calculateDistance(RankCoordinate other) {
        return Math.abs(this.rowNumber - other.rowNumber);
    }
}
