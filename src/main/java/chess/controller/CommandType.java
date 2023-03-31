package chess.controller;

import java.util.Arrays;
import java.util.Objects;

public enum CommandType {
    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end"),
    EMPTY("_empty");

    private final String code;

    CommandType(final String code) {
        this.code = code;
    }

    public static CommandType from(final String code) {
        return Arrays.stream(values())
                     .filter(commandType -> Objects.equals(code, commandType.code))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령어입니다"));
    }
}
