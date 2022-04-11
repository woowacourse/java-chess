package chess.controller;

import java.util.Arrays;

public enum InGameCommand {
    MOVE("move"),
    STATUS("status");

    private static final String UNVALID_COMMAND = "[ERROR] 유효하지 않은 명령입니다.";
    private final String commandName;

    InGameCommand(String commandName) {
        this.commandName = commandName;
    }

    public static InGameCommand of(String commandName) {
        return Arrays.stream(InGameCommand.values())
                .filter(it -> it.commandName.equals(commandName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNVALID_COMMAND));
    }
}
