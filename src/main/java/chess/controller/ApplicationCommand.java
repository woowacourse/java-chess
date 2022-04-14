package chess.controller;

import java.util.Arrays;

public enum ApplicationCommand {
    START("start"),
    END("end");

    private static final String UNVALID_COMMAND = "[ERROR] start 혹은 end 명령을 입력하세요.";
    private final String commandName;

    ApplicationCommand(String commandName) {
        this.commandName = commandName;
    }

    public static ApplicationCommand of(String commandName) {
        return Arrays.stream(ApplicationCommand.values())
                .filter(it -> it.commandName.equals(commandName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNVALID_COMMAND));
    }
}
