package chess.controller;

import java.util.Arrays;

public enum Command {
    START, MOVE, END;

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(command -> command.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
