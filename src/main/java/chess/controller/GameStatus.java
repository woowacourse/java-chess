package chess.controller;

import java.util.Arrays;


public enum GameStatus {
    INIT,
    START,
    MOVE,
    END;

    public static final String INVALID_COMMAND_MESSAGE = "잘못된 커맨드를 입력하였습니다.";
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    public static final int VALID_MOVE_INPUT_SIZE = 3;
    public static final int VALID_POSITION_INPUT_SIZE = 2;

    public static GameStatus from(String command) {
        final String target = command.toUpperCase();
        if (target.startsWith(MOVE.name())) {
            validate(command);
        }
        return Arrays.stream(values())
                .filter(value -> target.startsWith(value.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND_MESSAGE));
    }

    private static void validate(final String moveCommand) {
        final String[] split = moveCommand.split(" ");
        validateSize(split);
        validatePositionFormat(split);
    }

    private static void validateSize(final String[] split) {
        if (split.length!= VALID_MOVE_INPUT_SIZE) {
            throw new IllegalArgumentException("'move source위치 target위치' 형식으로 입력해야 합니다.");
        }
    }

    private static void validatePositionFormat(final String[] split) {
        if (split[SOURCE_INDEX].length() != VALID_POSITION_INPUT_SIZE ||
                split[TARGET_INDEX].length() != VALID_POSITION_INPUT_SIZE) {
            throw new IllegalArgumentException("source위치와 target위치 입력 형식이 올바르지 않습니다.");
        }
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean playable() {
        return this != END;
    }
}
