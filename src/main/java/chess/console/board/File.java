package chess.console.board;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum File {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H");

    private final String fileName;

    File(String fileName) {
        this.fileName = fileName;
    }

    public static File from(String fileInput) {
        return Arrays.stream(values())
                .filter(file -> file.fileName.equalsIgnoreCase(fileInput))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
