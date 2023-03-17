package chessgame.domain.point;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum File {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private static final Map<Character, File> FILE_MAP = Collections.unmodifiableMap(Stream.of(values()).collect(
        Collectors.toMap(File::getValue, Function.identity())));

    private final char value;

    File(char value) {
        this.value = value;
    }

    public static File find(char value) {
        if (FILE_MAP.containsKey(value)) {
            return FILE_MAP.get(value);
        }
        throw new IllegalArgumentException("file 좌표가 잘못되었습니다.");
    }

    public int distance(File target) {
        return this.value - target.value;
    }

    public File move(int fileMove) {
        char result = (char)(value + fileMove);

        return find(result);
    }

    private char getValue() {
        return value;
    }
}
