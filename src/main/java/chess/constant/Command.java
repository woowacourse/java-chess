package chess.constant;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    public static final String NOT_FOUND_COMMAND_EXCEPTION = "[ERROR] 이 명령문은 존재하지 않습니다.";

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(command -> input.toLowerCase().startsWith(command.name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COMMAND_EXCEPTION));
    }

    public static boolean isEnd(Command command) {
        return END == command;
    }

    public static boolean isStatus(Command command) {
        return STATUS == command;
    }
}
