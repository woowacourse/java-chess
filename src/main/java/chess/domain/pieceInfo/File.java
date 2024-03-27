package chess.domain.pieceInfo;

import java.util.Arrays;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String file;
    private final int index;

    File(final String file, final int index) {
        this.file = file;
        this.index = index;
    }

    public static File valueByFileIndex(final String fileIndex) {
        return Arrays.stream(values())
                .filter(value -> value.file.equals(fileIndex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("보드의 범위를 벗어난 좌표입니다."));
    }

    public static File valueByIndex(final int index) {
        return Arrays.stream(values())
                .filter(value -> value.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("보드의 범위를 벗어난 좌표입니다."));
    }

    public int getIndex() {
        return index;
    }
}
