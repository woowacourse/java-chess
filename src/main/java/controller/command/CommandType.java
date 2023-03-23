package controller.command;

import java.util.Arrays;

public enum CommandType {
    START,
    MOVE,
    END;

    public static CommandType from(String input) {
        return Arrays.stream(values())
                .filter(s -> input.equalsIgnoreCase(s.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move만 가능합니다."));
    }
}
