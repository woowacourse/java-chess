package chess.controller;

import chess.model.exception.GameCommandNotFoundException;
import java.util.Map;

public enum GameCommand {

    START("start"),
    STATUS("status"),
    LOAD("load"),
    MOVE("move"),
    END("end");

    public static final int MOVE_PARAMETER_SIZE = 2;
    public static final int LOAD_PARAMETER_SIZE = 1;
    private static final Map<String, GameCommand> COMMAND_MAPPER = Map.of(
            START.value, START, STATUS.value, STATUS, LOAD.value, LOAD,
            MOVE.value, MOVE, END.value, END
    );

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }

    public static GameCommand from(final String commandString) {
        if (COMMAND_MAPPER.containsKey(commandString)) {
            throw new GameCommandNotFoundException();
        }
        return COMMAND_MAPPER.get(commandString);
    }
}
