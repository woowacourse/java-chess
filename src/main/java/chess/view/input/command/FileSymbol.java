package chess.view.input.command;

import java.util.Arrays;

public enum FileSymbol {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String symbol;
    private final int file;

    FileSymbol(final String symbol, final int file) {
        this.symbol = symbol;
        this.file = file;
    }

    public static FileSymbol getFileSymbol(final String symbol) {
        return Arrays.stream(FileSymbol.values()).filter(fileSymbol -> fileSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("파일은 알파벳 소문자 a ~ h 까지 변환 가능합니다."));
    }

    public int getFile() {
        return file;
    }
}

