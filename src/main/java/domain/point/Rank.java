package domain.point;

import java.util.Arrays;

public enum Rank {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String symbol;

    Rank(String symbol) {
        this.symbol = symbol;
    }

    public static Rank findBySymbol(String symbol) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 랭크 값입니다."));
    }

    public String getSymbol() {
        return symbol;
    }
}
