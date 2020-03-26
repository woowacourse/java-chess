package chess.domain;

import util.NullChecker;

import java.util.Arrays;
import java.util.Objects;

public enum GameState {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String name;

    GameState(String name) {
        this.name = name;
    }

    public static GameState of(String input) {
        NullChecker.validateNotNull(input);
        return Arrays.stream(GameState.values())
                .filter(gameState -> gameState.getName().equals(input.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다"));
    }

    public String getName() {
        return name;
    }
}
