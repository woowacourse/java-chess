package chess.controller;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String command) {
        return Arrays.stream(GameCommand.values())
                .filter(it -> it.command.equalsIgnoreCase(command))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력은 Start 와 End 만 가능합니다"));
    }
}
