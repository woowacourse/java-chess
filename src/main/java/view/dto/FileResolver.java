package view.dto;

import java.util.Arrays;

public enum FileResolver {

    A(1, 'a'),
    B(2, 'b'),
    C(3, 'c'),
    D(4, 'd'),
    E(5, 'e'),
    F(6, 'f'),
    G(7, 'g'),
    H(8, 'h'),
    ;

    private final int file;
    private final char rawFile;

    FileResolver(int file, char rawFile) {
        this.file = file;
        this.rawFile = rawFile;
    }

    public static int resolveFile(char rawFile) {
        return Arrays.stream(values())
                .filter(value -> value.rawFile == rawFile)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 file입니다."))
                .file;
    }
}
