package controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("END"),
    MOVE("move");

    private final String value;

    Command(String value) {
        this.value = value;
    }


    public static Command findRunCommand(String command) {
        return Arrays.stream(Command.values())
                .filter(runCommand -> runCommand.value.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }
}
