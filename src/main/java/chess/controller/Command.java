package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(command -> command.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다."));
    }
}
