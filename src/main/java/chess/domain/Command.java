package chess.domain;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command renderToCommand(String input) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다."));
    }

    public boolean isStartCommand() {
        return this == START;
    }

    public boolean isEndCommand() {
        return this == END;
    }

    public boolean isStatusCommand() {
        return this == STATUS;
    }
}
