package domain.point;

import java.util.Arrays;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String symbol;

    File(String symbol) {
        this.symbol = symbol;
    }

    public static File findBySymbol(String symbol) {
        return Arrays.stream(File.values())
                .filter(file -> file.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("우효하지 않은 파일 값입니다."));
    }

    public String getSymbol() {
        return symbol;
    }
}
