package view.format.command;

import java.util.Arrays;

public enum StartCommandsFormat {
    START,
    QUIT,
    CONTINUE,
    RECORD;

    public static StartCommandsFormat of(final String input) {
        return Arrays.stream(values())
                .filter(command -> command.name().toLowerCase().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드 입니다."));
    }
}
