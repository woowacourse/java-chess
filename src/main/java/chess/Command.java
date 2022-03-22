package chess;

import java.util.Arrays;

public enum Command {
    START("START"),
    END("END");

    static final String NOT_FOUND_COMMAND_EXCEPTION = "[ERROR] 이 명령문은 존재하지 않습니다.";
    private String name;

    Command(String name) {
        this.name = name;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
            .filter(value -> input.equalsIgnoreCase(value.name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COMMAND_EXCEPTION));
    }
}
