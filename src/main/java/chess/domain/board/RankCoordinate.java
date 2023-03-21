package chess.domain.board;

import java.util.Arrays;

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

    private final int rowNumber;

    RankCoordinate(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public static RankCoordinate findBy(int rowNumber) {
        return Arrays.stream(values())
                .filter(it -> it.rowNumber == rowNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 행 번호를 입력해주세요."));
    }

    public int getRowNumber() {
        return rowNumber;
    }
}
