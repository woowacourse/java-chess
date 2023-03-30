package controller.command;

import java.util.Arrays;

public enum CommandType {
    START,
    MOVE,
    END,
    STATUS;

    public static CommandType from(String input) {
        return Arrays.stream(values())
                .filter(s -> input.equalsIgnoreCase(s.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move, status만 가능합니다."));
    }
}
