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
            .filter(value -> input.equals(value.command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 명령어 입니다."));
    }
}
