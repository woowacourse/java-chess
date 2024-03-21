package chess.domain.position;

import java.util.Arrays;

public enum ChessRank {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);

    private final String value;
    private final int index;

    ChessRank(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static int minIndex() {
        return ONE.index;
    }

    public static int maxIndex() {
        return EIGHT.index();
    }

    public static ChessRank findByValue(String rankValue) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(rankValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스 랭크 범위에 해당하지 않는 값입니다."));
    }

    public static ChessRank findByIndex(int rankIndex) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == rankIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("체스 랭크 범위에 해당하지 않는 인덱스입니다."));
    }

    public int index() {
        return index;
    }
}
