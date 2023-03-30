package chess.controller.command;

import chess.exception.ChessException;
import chess.exception.ExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    START("start", 0),
    END("end", 0),
    MOVE("move", 2),
    STATUS("status", 0);

    private static final Map<String, CommandType> TYPE_BY_INPUT = new HashMap<>();

    static {
        for (CommandType type : values()) {
            TYPE_BY_INPUT.put(type.value, type);
        }
    }

    private final String value;
    private final int requiredParameterNumber;

    CommandType(final String value, final int requiredParameterNumber) {
        this.value = value;
        this.requiredParameterNumber = requiredParameterNumber;
    }

    public static CommandType findBy(String inputValue) {
        return TYPE_BY_INPUT.computeIfAbsent(inputValue,
                s -> {
                    throw new ChessException(ExceptionCode.UNDEFINED_COMMAND_TYPE);
                }
        );
    }

    public int getRequiredParameterNumber() {
        return requiredParameterNumber;
    }
}
