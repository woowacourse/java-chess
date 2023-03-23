package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public static Command from(final String input) {
        return Arrays.stream(Command.values())
                .filter(value -> value.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move 중에 입력해주세요."));
    }
}
