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

    private static final String UNKNOWN_VALUE = "체스 랭크 범위에 해당하지 않는 값입니다.";
    private static final String UNKNOWN_INDEX = "체스 랭크 범위에 해당하지 않는 인덱스입니다.";

    private final String value;
    private final int index;

    ChessRank(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static int minIndex() {
        return ONE.index;
    }

    public static int maxIndex() {
        return EIGHT.index();
    }

    public static ChessRank findByValue(final String rankValue) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(rankValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_VALUE));
    }

    public static ChessRank findByIndex(final int rankIndex) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == rankIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_INDEX));
    }

    public ChessRank move(final ChessDirection chessDirection) {
        if (chessDirection.isUpSide()) {
            return findByIndex(this.index + 1);
        }
        if (chessDirection.isHorizontal()) {
            return this;
        }
        return findByIndex(this.index - 1);
    }

    public String value() {
        return value;
    }

    public int index() {
        return index;
    }

    public int differenceTo(final ChessRank rank) {
        return this.index - rank.index;
    }
}
