package chess.domain;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    MOVE("move"),
    END("end");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public String get() {
        return commandType;
    }

    public static CommandType getCommand(String command) {
        return Arrays.stream(CommandType.values())
                .filter(value -> value.get().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("커맨드 타입은 start, end, move 중 하나여야 합니다."));
    }
}
