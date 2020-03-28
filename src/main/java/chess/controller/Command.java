package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(final String command) {
        return Arrays.stream(values())
                .filter(c -> command.contains(c.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어를 입력하였습니다."));
    }

    public boolean isStart() {
        return this == START;
    }
}
