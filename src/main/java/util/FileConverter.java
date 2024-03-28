package util;

import java.util.Arrays;

public enum FileConverter {
    A('a', 1),
    B('b', 2),
    C('c', 3),
    D('d', 4),
    E('e', 5),
    F('f', 6),
    G('g', 7),
    H('h', 8);

    private final char fileSymbol;
    private final int fileValue;

    FileConverter(char fileSymbol, int fileValue) {
        this.fileSymbol = fileSymbol;
        this.fileValue = fileValue;
    }

    public static FileConverter from(char inputFileSymbol) {
        return Arrays.stream(FileConverter.values())
                .filter(fileConverter -> fileConverter.fileSymbol == inputFileSymbol)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력한 File는 올바르지 않은 값입니다"));
    }

    public int getValue() {
        return fileValue;
    }
}
