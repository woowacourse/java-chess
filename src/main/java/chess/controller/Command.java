package chess.controller;

import java.util.Arrays;

public enum Command {
    END("end"),
    PLAY("move"),
    START("start"),
    EMPTY(""),
    ;

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command from(String targetCommand) {
        return Arrays.stream(Command.values())
                .filter(command -> command != EMPTY)
                .filter(command -> command.value.equals(targetCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 커맨드를 입력해주세요."));
    }
}
