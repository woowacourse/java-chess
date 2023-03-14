package controller;

import java.util.Arrays;

public enum Command {

    START("start"),
    END("end");

    private final String message;

    Command(final String message) {
        this.message = message;
    }

    public static Command of(final String targetMessage) {
        return Arrays.stream(values())
                .filter(command -> command.message.equals(targetMessage))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 명령입니다."));
    }

    public boolean isStart() {
        return this == Command.START;
    }
}
