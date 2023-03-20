package chess;

import java.util.Arrays;
import java.util.List;

public enum ChessGameCommand {
    EMPTY,
    START,
    MOVE,
    STATUS,
    END,
    ;

    public static final int COMMAND_INDEX = 0;

    public static ChessGameCommand from(List<String> input) {
        return Arrays.stream(values())
                .filter(state -> state != EMPTY)
                .filter(state -> state.name().equalsIgnoreCase(input.get(COMMAND_INDEX)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 명령어가 없음!"));
    }

    public boolean isPlayable() {
        return this != END;
    }
}
