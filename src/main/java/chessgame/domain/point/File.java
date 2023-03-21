package chessgame.domain.point;

import java.util.HashMap;
import java.util.Map;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private static final Map<Character, File> FILE_MAP = new HashMap<>();

    static {
        for (File file : values()) {
            FILE_MAP.put(file.value, file);
        }
    }

    private final char value;

    File(char value) {
        this.value = value;
    }

    public static File findFile(char result) {
        if (FILE_MAP.containsKey(result)) {
            return FILE_MAP.get(result);
        }
        throw new IllegalArgumentException("file 좌표가 잘못되었습니다.");
    }

    public int distance(File target) {
        return this.value - target.value;
    }

    public File move(int fileMove) {
        char result = (char) (value + fileMove);

        return findFile(result);
    }
}
