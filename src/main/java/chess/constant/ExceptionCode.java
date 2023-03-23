package chess.constant;

import java.util.HashMap;
import java.util.Map;

public enum ExceptionCode {
    INVALID_COMMAND_PARAMETER,
    UNDEFINED_COMMAND_TYPE,
    PIECE_CAN_NOT_FOUND,
    PIECE_MOVING_PATH_BLOCKED,
    ACCESS_BLANK_PIECE,
    INVALID_DESTINATION,
    TARGET_IS_SAME_COLOR,
    GAME_END,
    GAME_NOT_INITIALIZED,
    INVALID_COMMAND,
    GAME_ALREADY_RUNNING,
    INVALID_TURN;

    private static final Map<String, ExceptionCode> CODE_BY_NAME = new HashMap<>();

    static {
        for (ExceptionCode code : values()) {
            CODE_BY_NAME.put(code.name(), code);
        }
    }

    public static ExceptionCode findByName(String name) {
        return CODE_BY_NAME.get(name);
    }
}
