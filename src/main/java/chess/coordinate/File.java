package chess.coordinate;

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
    private final int value;

    File(final String symbol, final int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static File findByValue(int value) {
        return Arrays.stream(values())
                .filter(aFile -> aFile.value == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public int subtract(File file) {
        return this.value - file.value;
    }

    public File sum(int fileValue) {
        return findByValue(this.value + fileValue);
    }

    public String getSymbol() {
        return symbol;
    }
}
