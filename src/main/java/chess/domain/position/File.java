package chess.domain.position;

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

    File(String file, int index) {
        this.file = file;
        this.index = index;
    }

    public static File of(String file) {
        return Arrays.stream(values())
            .filter(value -> file.equals(value.file))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 범위를 벗어났습니다."));
    }

    public static File of(int file) {
        return Arrays.stream(values())
            .filter(value -> file == value.index)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 범위를 벗어났습니다."));
    }

    public String getFile() {
        return file;
    }

    public int getIndex() {
        return index;
    }
}
