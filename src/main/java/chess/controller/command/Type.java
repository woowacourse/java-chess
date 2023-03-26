package chess.controller.command;

import chess.constant.ExceptionCode;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    START("start", 0),
    END("end", 0),
    MOVE("move", 2),
    STATUS("status", 0);

    private static final Map<String, Type> TYPE_BY_INPUT = new HashMap<>();

    static {
        for (Type type : values()) {
            TYPE_BY_INPUT.put(type.value, type);
        }
    }

    private final String value;
    private final int requiredParameterNumber;

    Type(final String value, final int requiredParameterNumber) {
        this.value = value;
        this.requiredParameterNumber = requiredParameterNumber;
    }

    public static Type findBy(String inputValue) {
        return TYPE_BY_INPUT.computeIfAbsent(inputValue,
                s -> {
                    throw new IllegalArgumentException(ExceptionCode.UNDEFINED_COMMAND_TYPE.name());
                }
        );
    }

    public int getRequiredParameterNumber() {
        return requiredParameterNumber;
    }
}
