package chess.controller;

import java.util.Map;

public enum GameCommand {

    START("start"),
    STATUS("status"),
    LOAD("load"),
    MOVE("move"),
    END("end");

    public static final int MOVE_COMMAND_SIZE = 3;
    public static final int LOAD_COMMAND_SIZE = 2;
    public static final int DEFAULT_COMMAND_SIZE = 1;
    private static final String COMMAND_NOT_FOUND_MESSAGE = "해당하는 명령어를 찾을 수 없습니다.";
    private static final Map<String, GameCommand> COMMAND_MAPPER = Map.of(
            START.value, START, START.value, STATUS, LOAD.value, LOAD,
            MOVE.value, MOVE, END.value, END
    );

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }

    public static GameCommand from(final String commandString) {
        if (COMMAND_MAPPER.containsKey(commandString)) {
            throw new IllegalArgumentException(COMMAND_NOT_FOUND_MESSAGE);
        }
        return COMMAND_MAPPER.get(commandString);
    }
}
