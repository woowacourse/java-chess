package util;

import java.util.Arrays;

public enum RankConverter {
    ONE('1', 1),
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8);

    private final char rankSymbol;
    private final int rankValue;

    RankConverter(char rankSymbol, int rankValue) {
        this.rankSymbol = rankSymbol;
        this.rankValue = rankValue;
    }

    public static RankConverter from(char inputFileSymbol) {
        return Arrays.stream(RankConverter.values())
                .filter(rankConverter -> rankConverter.rankSymbol==inputFileSymbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력한 rank는 올바르지 않은 값입니다."));
    }

    public int getValue() {
        return rankValue;
    }
}
