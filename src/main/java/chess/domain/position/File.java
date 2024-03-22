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
    H(8, "h"),
    ;

    private final int value;
    private final String command;

    File(int value, String command) {
        this.value = value;
        this.command = command;
    }

    public static File from(int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File입니다."));
    }

    public File add(int x) {
        int newX = this.value + x;
        return File.from(newX);
    }

    public boolean isInRange(int nextFile) {
        return A.value <= nextFile && nextFile <= H.value;
    }

    public int getValue() {
        return value;
    }

    public String getCommand() {
        return command;
    }
}
