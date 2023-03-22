package controller;

import java.util.Arrays;
import java.util.List;

public enum Command {

    START("start", 1),
    END("end", 1),
    STATUS("status", 1),
    MOVE("move", 3),
    ;

    private final String message;
    private final int commandCount;

    Command(final String message, final int commandCount) {
        this.message = message;
        this.commandCount = commandCount;
    }

    public static Command of(final List<String> targetMessage) {
        return Arrays.stream(values())
                .filter(command -> command.message.equals(targetMessage.get(0)))
                .filter(command -> targetMessage.size() == command.commandCount)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 명령입니다."));
    }

    public boolean isStart() {
        return this == Command.START;
    }

    public boolean isNotEnd() {
        return this != Command.END &&
                this != Command.STATUS;
    }

    public boolean isMove() {
        return this == Command.MOVE;
    }
}
