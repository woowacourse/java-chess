package chess.domain.position;

import java.util.Arrays;

public enum File {

    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int value;
    private final String command;

    File(final int value, final String command) {
        this.value = value;
        this.command = command;
    }

    static File from(int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("파일의 위치 값은 1~8로 입력할 수 있습니다."));
    }

    public static File from(String command) {
        return Arrays.stream(values())
                .filter(file -> file.command.equalsIgnoreCase(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("파일 값은 A~H로 입력해 주세요."));
    }

    int gapWith(File file) {
        return this.value - file.value;
    }

    public int value() {
        return value;
    }

    public String command() {
        return command;
    }
}
