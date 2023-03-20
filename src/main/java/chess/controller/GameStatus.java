package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum GameStatus {
    INIT,
    START,
    MOVE,
    END;

    public static final String INVALID_COMMAND_MESSAGE = "잘못된 커맨드를 입력하였습니다.";
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    private static final int DEFAULT_COMMAND_INDEX = 0;
    public static final int SOURCE_AND_TARGET_LENGTH = 2;
    public static final int INPUT_SIZE = 3;

    public static GameStatus from(final List<String> input) {
        GameStatus result = Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(input.get(DEFAULT_COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
        if (result == MOVE) {
            validateMoveCommand(input);
        }
        return result;
    }

    private static void validateMoveCommand(List<String> input) {
        if (input.size() != INPUT_SIZE) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
        if (input.get(SOURCE_INDEX).length() != SOURCE_AND_TARGET_LENGTH ||
                input.get(TARGET_INDEX).length() != SOURCE_AND_TARGET_LENGTH) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
    }

    public boolean isEnd() {
        return this == END;
    }
}
