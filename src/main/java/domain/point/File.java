package domain.point;

import java.util.Arrays;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String symbol;
    private final int indexFromLeft;

    File(String symbol, int indexFromLeft) {
        this.symbol = symbol;
        this.indexFromLeft = indexFromLeft;
    }

    public static File findBySymbol(String symbol) {
        return Arrays.stream(File.values())
                .filter(file -> file.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("우효하지 않은 파일 값입니다."));
    }

    public int getIndexFromLeft() {
        return indexFromLeft;
    }

    public String getSymbol() {
        return symbol;
    }
}
