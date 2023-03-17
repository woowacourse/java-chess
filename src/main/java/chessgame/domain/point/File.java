package chessgame.domain.point;

import java.util.Arrays;
import java.util.Optional;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char value;

    File(char value) {
        this.value = value;
    }

    public static Optional<File> findFile(char result) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == result)
                .findFirst();
    }

    public int distance(File target) {
        return this.value - target.value;
    }

    public Optional<File> move(int fileMove) {
        char result = (char) (value + fileMove);

        return findFile(result);
    }
}
