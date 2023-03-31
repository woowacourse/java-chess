package chess.domain;

import java.util.Arrays;

public enum GameStatus {
    WAIT,
    START,
    MOVE,
    CATCH,
    END,
    NONE;

    GameStatus() {
    }
}
