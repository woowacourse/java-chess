package chess.domain.game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    public static final String INVALID_COMMAND = "올바른 명령어가 아닙니다.";

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static Command findCommand(String input) {
        return findCommand(parseCommand(input));
    }

    public static Command findCommand(List<String> input) {
        return Arrays.stream(values())
            .filter(value -> value.command.equals(input.get(0)))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND));
    }

    public static List<String> parseCommand(String input) {
        return Arrays.stream(input.split("\\s"))
            .map(String::trim)
            .collect(Collectors.toList());
    }
}
