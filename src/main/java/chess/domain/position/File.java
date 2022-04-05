package chess.domain.position;

import java.util.Arrays;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7)
    ;

    private final String notation;
    private final int index;

    File(String notation, int index) {
        this.notation = notation;
        this.index = index;
    }

    public static File of(String value) {
        return Arrays.stream(File.values())
                .filter(file -> file.notation.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다."));
    }

    public static File of(int index) {
        return Arrays.stream(File.values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다."));
    }

    public String getNotation() {
        return notation;
    }

    public int getIndex() {
        return index;
    }
}
