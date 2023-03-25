package controller;

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

    public static Command find(String command) {
        return Arrays.stream(Command.values()).filter(value -> value.command.equals(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    public String getCommand() {
        return command;
    }
}
