package chess.domain;

import java.util.Arrays;

public enum File {

    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String index;

    File(final String index) {
        this.index = index;
    }

    public static File from(final String fileIndex) {
        return Arrays.stream(File.values())
                .filter(file -> file.index.equals(fileIndex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 인덱스입니다."));
    }
}

