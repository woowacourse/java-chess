package chess.controller;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }

    public final GameCommand from(final String command) {
        return Arrays.stream(GameCommand.values())
                .filter(gameCommand -> gameCommand.value.equals(command))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
