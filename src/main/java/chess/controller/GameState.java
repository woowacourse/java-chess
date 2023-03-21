package chess.controller;

import java.util.Arrays;

public enum GameState {
    READY("start"),
    END("end"),
    RUNNING("move");

    private final String command;

    GameState(final String command) {
        this.command = command;
    }

    public static GameState valueOfCommand(String inputCommand) {
        return Arrays.stream(GameState.values())
                .filter(gameState -> gameState.command.equals(inputCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다. 입력 값 :" + inputCommand));
    }
}
