package chess.domain.position;

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

    private final String file;

    File(String file) {
        this.file = file;
    }

    public static File of(final String file) {
        return Arrays.stream(File.values())
            .filter(value -> value.getFile().equals(file))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 File 값 입니다."));
    }

    public String getFile() {
        return file;
    }
}
