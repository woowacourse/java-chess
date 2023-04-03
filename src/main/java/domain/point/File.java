package domain.point;

import domain.util.ExceptionMessages;

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
    private final int index;

    File(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public File left() {
        if (this == A) {
            throw new IllegalArgumentException(ExceptionMessages.POINT_OUT_OF_BOARD);
        }

        int index = parseSymbolToIndex();
        return findByIndex(index - 1);
    }

    public File right() {
        if (this == H) {
            throw new IllegalArgumentException(ExceptionMessages.POINT_OUT_OF_BOARD);
        }

        int index = parseSymbolToIndex();
        return findByIndex(index + 1);
    }

    private int parseSymbolToIndex() {
        return symbol.charAt(0) - 97;
    }

    private File findByIndex(int index) {
        return Arrays.stream(File.values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 파일의 인덱스입니다."));
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
