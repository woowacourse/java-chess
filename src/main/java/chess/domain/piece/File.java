package chess.domain.piece;

import java.util.Arrays;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int index;

    File(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public static File fromSymbol(final String input) {
        return Arrays.stream(values())
                .filter(file -> file.symbol.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 파일 입력이 아닙니다."));
    }

    public static File fromIndex(final int input) {
        return Arrays.stream(values())
                .filter(file -> file.index == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효한 파일 입력이 아닙니다."));
    }

    public File right() {
        return fromIndex(this.index + 1);
    }

    public File left() {
        return fromIndex(this.index - 1);
    }

    public int getDistance(final File other) {
        return Math.abs(this.index - other.index);
    }

    public boolean isBigger(final File other) {
        return this.index > other.index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "" + symbol;
    }
}
