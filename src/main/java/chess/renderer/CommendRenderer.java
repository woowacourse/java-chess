package chess.renderer;

import chess.controller.command.CommandType;

import java.util.Arrays;

public enum CommendRenderer {
    START("start", CommandType.START),
    END("end", CommandType.END),
    MOVE("move", CommandType.MOVE),
    STATUS("status", CommandType.STATUS);
    private final String value;
    private final CommandType commandType;

    CommendRenderer(String value, CommandType commandType) {
        this.value = value;
        this.commandType = commandType;
    }

    public static CommandType render(String input) {
        return Arrays.stream(values())
                .filter(commendType -> commendType.value.equals(input))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .commandType;
    }
}
