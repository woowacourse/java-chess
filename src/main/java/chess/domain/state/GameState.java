package chess.domain.state;

import java.util.Arrays;
import util.NullChecker;

public enum GameState {
    START("start", true),
    END("end", false),
    MOVE("move", true),
    STATUS("status", true);

    private final String name;
    private final boolean loop;

    GameState(String name, boolean loop) {
        this.name = name;
        this.loop = loop;
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

    public boolean isContinue() {
        return loop;
    }
}
