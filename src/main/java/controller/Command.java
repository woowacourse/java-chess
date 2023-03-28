package controller;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ENTER("enter");

    private final String input;

    Command(final String input) {
        this.input = input;
    }

    public static Command find(final String input) {
        return Arrays.stream(Command.values())
            .filter(command -> command.input.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 입력입니다."));
    }
}
