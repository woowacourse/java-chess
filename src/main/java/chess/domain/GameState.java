package chess.domain;

import java.util.Arrays;

public enum GameState {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    ERROR("입력이 잘못되었습니다.");

    private final String name;

    GameState(String name) {
        this.name = name;
    }

    public static GameState of(String input) {
        validateInput(input);
        return Arrays.stream(GameState.values())
                .filter(gameState -> gameState.getName().equals(input.toLowerCase()))
                .findFirst()
                .orElse(ERROR);
    }

    private static void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 존재하지 않습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
