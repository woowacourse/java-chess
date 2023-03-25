package chess.controller;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end"),
    STATUS("status"),
    MOVE("move");

    private final String name;

    Command(final String name) {
        this.name = name;
    }

    public static Command findByString(final String name) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
    }
}
