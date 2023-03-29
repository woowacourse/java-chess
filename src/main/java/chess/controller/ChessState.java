package chess.controller;

import java.util.Arrays;

public enum ChessState {

    INIT,
    START,
    PROGRESS,
    END;

    private static final String NOT_EXIST_COMMAND_ERROR_MESSAGE = "해당하는 게임 진행 상태가 존재하지 않습니다";

    public static ChessState bind(final String value) {
        return Arrays.stream(ChessState.values())
                .filter(state -> state.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_COMMAND_ERROR_MESSAGE));
    }
}
