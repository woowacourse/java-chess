package chess.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum FileSymbol {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String fileSymbol;

    FileSymbol(String fileSymbol) {
        this.fileSymbol = fileSymbol;
    }

    public static FileSymbol convertToFileSymbol(String fileSymbol) {
        return Arrays.stream(FileSymbol.values())
                .filter(file -> file.fileSymbol.equals(fileSymbol))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 file 값입니다."));
    }
}
