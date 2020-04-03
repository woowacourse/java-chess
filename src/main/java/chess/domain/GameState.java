package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum GameState {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static Map<String, GameState> CACHE = new HashMap<>();
    private final String name;

    GameState(String name) {
        this.name = name;
    }

    static {
        for (GameState state : GameState.values()) {
            CACHE.putIfAbsent(state.getName(), state);
        }
    }

    public static GameState of(String input) {
        validateInput(input);
        return CACHE.get(input);
    }

    private static void validateInput(String input) {
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
        if (!CACHE.containsKey(input)) {
            throw new IllegalArgumentException("잘못된 명령어입니다");
        }
    }

    public String getName() {
        return name;
    }
}
