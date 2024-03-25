package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int file;
    private final String fileSymbol;

    File(int file, String fileSymbol) {
        this.file = file;
        this.fileSymbol = fileSymbol;
    }

    public static File convertToFile(String fileSymbol) {
        return Arrays.stream(File.values()).filter(start -> start.fileSymbol.equals(fileSymbol))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 file 값입니다."));
    }

    public int calculateDifference(File file) {
        return file.file - this.file;
    }

    public File move(int moveUnit) {
        int destination = file + moveUnit;
        return Arrays.stream(File.values()).filter(start -> start.file == destination)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 file 값입니다."));
    }

    public String getSymbol() {
        return fileSymbol;
    }
}
