package chess.controller;

import java.util.Arrays;

public enum GameState {
    READY("start"),
    RUNNING("move"),
    END("end"),
    CHECKING("status");

    private final String commandHead;

    GameState(final String commandHead) {
        this.commandHead = commandHead;
    }

    public static GameState valueOfCommand(String input) {
        return Arrays.stream(GameState.values())
                .filter(gameState -> gameState.commandHead.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다. 입력 값 :" + input));
    }
}
