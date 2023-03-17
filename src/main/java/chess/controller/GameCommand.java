package chess.controller;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private static final String COMMAND_NOT_FOUND_MESSAGE = "해당하는 명령어가 없습니다.";

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }

    public static GameCommand from(final String commandString) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.value.equals(commandString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND_MESSAGE));
    }
}
