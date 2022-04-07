package console.controller;

import java.util.Arrays;

public enum Command {

    START("start"),
    FINISH("end"),
    MOVE("move"),
    STATUS("status");

    private final String input;

    Command(String input) {
        this.input = input;
    }

    public static Command find(String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 명령어가 없습니다."));
    }
}
