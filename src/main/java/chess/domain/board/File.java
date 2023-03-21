package chess.domain.board;

import java.util.Arrays;

public enum File {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h'),
    ;

    private final char index;

    File(final char index) {
        this.index = index;
    }

    public static File from(final char fileIndex) {
        return Arrays.stream(File.values())
                .filter(file -> file.index == fileIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 인덱스입니다."));
    }

    public File next() {
        return File.from((char) (index + 1));
    }

    public File prev() {
        return File.from(((char) (index - 1)));
    }

    public int calculateDistance(final File file) {
        return this.index - file.index;
    }
}

