package chess.controller.dto;

import java.util.Arrays;

public enum SubCommand {
    NEW("new"),
    LOAD("load"),
    SAVE("save"),
    EMPTY("");

    private String subCommand;

    SubCommand(String subCommand) {
        this.subCommand = subCommand;
    }

    public static SubCommand of(String name) {
        return Arrays.stream(values())
                .filter(command -> command.subCommand.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
