package chess.controller;

import java.util.Arrays;
import java.util.Objects;

public enum Command {
    START("start"),
    END("end");

    private final String code;

    Command(final String code) {
        this.code = code;
    }

    public static Command from(String code) {
        return Arrays.stream(values())
                     .filter(command -> Objects.equals(command.code, code))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }
}
