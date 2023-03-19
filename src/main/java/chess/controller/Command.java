package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command findCommandByString(String input) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 명령어가 없습니다"));
    }
}
