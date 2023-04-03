package domain.point;

import domain.util.ExceptionMessages;

import java.util.Arrays;

public enum Rank {
    ONE("1", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7);

    private final String symbol;
    private final int index;

    Rank(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public static Rank findBySymbol(String symbol) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 랭크 값입니다."));
    }

    private static Rank findByIndex(int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 랭크의 인덱스입니다."));
    }

    public Rank up() {
        if (this == EIGHT) {
            throw new IllegalArgumentException(ExceptionMessages.POINT_OUT_OF_BOARD);
        }

        int indexFromBottomOfNewRank = Integer.parseInt(symbol);
        return findByIndex(indexFromBottomOfNewRank);
    }

    public Rank down() {
        if (this == ONE) {
            throw new IllegalArgumentException(ExceptionMessages.POINT_OUT_OF_BOARD);
        }

        int index = Integer.parseInt(symbol);
        return findByIndex(index - 2);
    }

    public String getSymbol() {
        return symbol;
    }
}
