package chess.domain.board;

import java.util.Arrays;

public enum File {
    A("A", 1),
    B("B", 2),
    C("C", 3),
    D("D", 4),
    E("E", 5),
    F("F", 6),
    G("G", 7),
    H("H", 8),
    ;

    private final String command;
    private final int position;

    File(final String command, final int position) {
        this.command = command;
        this.position = position;
    }

    public static File from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("파일은 A ~ H 사이의 값이어야 합니다."));
    }

    public static File from(final int position) {
        return Arrays.stream(values())
                .filter(value -> value.position == position)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치 값은 1 ~ 8 사이의 값이어야 합니다."));
    }

    public int calculateGap(final File target) {
        return position - target.position;
    }
}
