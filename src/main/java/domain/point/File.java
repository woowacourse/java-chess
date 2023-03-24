package domain.point;

import domain.exception.PointOutOfBoardException;

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

    public File left() {
        if (this == A) {
            throw new PointOutOfBoardException();
        }

        int indexFromLeftOfNewFile = parseSymbolToIndexFromLeft();
        return findByIndexFromLeft(indexFromLeftOfNewFile - 1);
    }

    public File right() {
        if (this == H) {
            throw new PointOutOfBoardException();
        }

        int indexFromLeftOfNewFile = parseSymbolToIndexFromLeft();
        return findByIndexFromLeft(indexFromLeftOfNewFile + 1);
    }

    private int parseSymbolToIndexFromLeft() {
        return symbol.charAt(0) - 97;
    }

    private File findByIndexFromLeft(int index) {
        return Arrays.stream(File.values())
                .filter(file -> file.indexFromLeft == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 파일의 인덱스입니다."));
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
