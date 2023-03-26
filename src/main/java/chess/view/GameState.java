package chess.view;

import java.util.Arrays;

public enum GameState {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String command;

    GameState(final String command) {
        this.command = command;
    }

    public static GameState from(final String inputCommand) {
        return Arrays.stream(GameState.values())
                .filter(gameState -> gameState.command.equals(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move, status만 입력할 수 있습니다."));
    }

    public boolean isNotMoveState() {
        return this != MOVE;
    }
}
