package chess.domain;

import java.util.Arrays;

public enum File {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    ;

    private final String command;

    File(final String command) {
        this.command = command;
    }

    public static File from(final String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("파일은 1 ~ 8 사이의 값이어야 합니다."));
    }
}
