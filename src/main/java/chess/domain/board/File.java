package chess.domain.board;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum File {
    A("A", 1),
    B("B", 2),
    C("C", 3),
    D("D", 4),
    E("E", 5),
    F("F", 6),
    G("G", 7),
    H("H", 8);

    private final String fileName;
    private final int x;

    File(String fileName, int x) {
        this.fileName = fileName;
        this.x = x;
    }

    public static File from(String fileInput) {
        return Arrays.stream(values())
                .filter(file -> file.fileName.equalsIgnoreCase(fileInput))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public int dx(File another) {
        return another.x - this.x;
    }
}
