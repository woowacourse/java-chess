package view;

import domain.board.File;
import java.util.Arrays;

public enum FileResolver {

    A(new File(1), "a"),
    B(new File(2), "b"),
    C(new File(3), "c"),
    D(new File(4), "d"),
    E(new File(5), "e"),
    F(new File(6), "f"),
    G(new File(7), "g"),
    H(new File(8), "h"),
    ;

    private final File file;
    private final String fileText;

    FileResolver(File file, String fileText) {
        this.file = file;
        this.fileText = fileText;
    }

    public static File resolveFile(String text) {
        return Arrays.stream(values())
            .filter(value -> value.fileText.equals(text))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 file입니다."))
            .file;
    }
}
