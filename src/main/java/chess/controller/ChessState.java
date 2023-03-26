package chess.controller;

import java.util.Arrays;

public enum ChessState {

    INIT("INIT"),
    START("START"),
    PROGRESS("PROGRESS"),
    END("END");

    private final String value;

    ChessState(final String value) {
        this.value = value;
    }

    public static ChessState bind(final String value) {
        return Arrays.stream(ChessState.values())
                .filter(state -> state.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 게임 진행 상태가 존재하지 않습니다"));
    }

    public String getValue() {
        return value;
    }
}
