package chess.domain.point;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum File {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private static final Map<Character, File> POOL;

    static {
        POOL = Arrays.stream(File.values())
                .collect(Collectors.toMap(file -> file.file, Function.identity()));
    }

    private final char file;

    File(char file) {
        this.file = file;
    }

    public static File of(char character) {
        char lowerChar = Character.toLowerCase(character);
        if (!POOL.containsKey(lowerChar)) {
            throw new IllegalArgumentException(String.format("가로 위치는 %c ~ %c 사이의 값이어야 합니다.", minValue(), maxValue()));
        }
        return POOL.get(lowerChar);
    }

    int distance(File file) {
        return this.file - file.file;
    }

    File add(int fileValue) {
        char file = (char) (this.file + fileValue);
        return File.of(file);
    }

    boolean addable(int fileValue) {
        int addedFile = this.file + fileValue;
        return addedFile >= minValue() && addedFile <= maxValue();
    }

    public static char maxValue() {
        return H.file;
    }

    public static char minValue() {
        return A.file;
    }
}
