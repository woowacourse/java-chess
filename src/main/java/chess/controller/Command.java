package chess.controller;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move [a-h]\\d [a-h]\\d"),
    STATUS("status");

    private static final String NO_COMMEND_MESSAGE = "잘못된 커멘드 명령입니다.";

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String commandText) {
        return Arrays.stream(values())
                .filter(command -> Pattern.matches(command.value, commandText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_COMMEND_MESSAGE));
    }
}

