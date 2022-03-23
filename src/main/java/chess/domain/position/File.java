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

    private final String value;

    File(String value) {
        this.value = value;
    }

    public static File of(final String value) {
        return Arrays.stream(File.values())
            .filter(file -> file.getValue().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 File 값 입니다."));
    }

    public String getValue() {
        return value;
    }
}
