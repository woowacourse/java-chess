package chess.domain;

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

    private static final Map<Character, File> POOL = Map.of(
            'a', A, 'b', B, 'c', C, 'd', D, 'e', E, 'f', F, 'g', G, 'h', H
    );

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
        return 'h';
    }

    public static char minValue() {
        return 'a';
    }
}
