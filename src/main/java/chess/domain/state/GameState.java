package chess.domain.state;

import java.util.Arrays;
import util.NullChecker;

public enum GameState {
    START("start", true),
    END("end", false),
    MOVE("move", true),
    STATUS("status", true);

    private static final String WRONG_ARGUMENT_EXCEPTION_MESSAGE = "잘못된 인자입니다.";

    private final String name;
    private final boolean repeat;

    GameState(String name, boolean repeat) {
        this.name = name;
        this.repeat = repeat;
    }

    public static GameState of(String input) {
        NullChecker.validateNotNull(input);
        return Arrays.stream(GameState.values())
            .filter(gameState -> gameState.getName().equals(input.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_ARGUMENT_EXCEPTION_MESSAGE));
    }

    public String getName() {
        return name;
    }

    public boolean isContinue() {
        return repeat;
    }
}
