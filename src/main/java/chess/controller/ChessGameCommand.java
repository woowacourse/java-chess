package chess.controller;

import java.util.Arrays;

public enum ChessGameCommand {
    EMPTY,
    START,
    MOVE,
    STATUS,
    END,
    ;

    public static final int COMMAND_INDEX = 0;
    public static final int FROM_INDEX = 1;
    public static final int TO_INDEX = 2;
    public static final int DEFAULT_COMMAND_SIZE = 1;
    public static final int MOVE_COMMAND_SIZE = 3;

    public static ChessGameCommand from(String input) {
        return Arrays.stream(values())
                .filter(state -> state != EMPTY)
                .filter(state -> state.name().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 명령어가 없음!"));
    }

    public boolean isPlayable() {
        return this != END;
    }
}
