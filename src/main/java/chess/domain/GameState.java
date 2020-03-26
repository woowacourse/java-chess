package chess.domain;

import java.util.Arrays;
import java.util.Objects;

public enum GameState {
    START("start"),
    END("end"),
    MOVE("move");

    private final String name;

    GameState(String name) {
        this.name = name;
    }

    public static GameState of(String input) {
        validateInput(input);
        return Arrays.stream(GameState.values())
                .filter(gameState -> gameState.getName().equals(input.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다"));
    }

    private static void validateInput(String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    public String getName() {
        return name;
    }
}
