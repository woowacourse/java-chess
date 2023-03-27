package chess.domain.chessboard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FileIndex {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    final char index;

    FileIndex(final char index) {
        this.index = index;
    }

    static FileIndex of(final char value) {
        return Arrays.stream(FileIndex.values())
                .filter(it -> it.index == value)
                .findAny()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 " + value + " File 이 없습니다.");
                });
    }

    static List<FileIndex> files() {
        return Arrays.stream(FileIndex.values()).collect(Collectors.toUnmodifiableList());
    }
}
