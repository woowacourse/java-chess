package chess.controller.command;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    START("start", false),
    END("end", false),
    MOVE("move", true);

    static final String INVALID_COMMAND_MESSAGE = "잘못된 명령어 입력 입니다.";

    private static final Map<String, Type> TYPE_BY_INPUT = new HashMap<>();

    static {
        for (Type type : values()) {
            TYPE_BY_INPUT.put(type.value, type);
        }
    }

    private final String value;
    private final boolean isParameterAllowed;

    Type(final String value, final boolean isParameterAllowed) {
        this.value = value;
        this.isParameterAllowed = isParameterAllowed;
    }

    public static Type findBy(String inputValue) {
        return TYPE_BY_INPUT.computeIfAbsent(inputValue,
                s -> {
                    throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
                }
        );
    }

    public boolean isParameterAllowed() {
        return isParameterAllowed;
    }
}
