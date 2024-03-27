package chess.domain.position;

import java.util.Map;
import java.util.HashMap;

public enum File {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private static final Map<Integer, File> CACHE;

    static {
        CACHE = new HashMap<>();
        for (File file : File.values()) {
            CACHE.put(file.fileColumn, file);
        }
    }

    private final int fileColumn;
    final String fileSymbol;

    File(int fileColumn, String fileSymbol) {
        this.fileColumn = fileColumn;
        this.fileSymbol = fileSymbol;
    }

    public int calculateDifference(File file) {
        return file.fileColumn - this.fileColumn;
    }

    public File move(int moveUnit) {
        int destination = fileColumn + moveUnit;
        return CACHE.get(destination);
    }
}
