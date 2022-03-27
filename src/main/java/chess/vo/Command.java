package chess.vo;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end"),
    STATUS("status");

    static final String NOT_FOUND_COMMAND_EXCEPTION = "[ERROR] 이 명령문은 존재하지 않습니다.";
    private static final String REGEX_START_END = "(start)|(end)";
    private static final String REGEX_MOVE_STATUS_END = "(end)|(status)|(move [a-h][1-8] [a-h][1-8])";
    private static final Pattern PATTERN_START_END = Pattern.compile(REGEX_START_END);
    private static final Pattern PATTERN_END_MOVE = Pattern.compile(REGEX_MOVE_STATUS_END);

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command startEnd(String input) {
        return Arrays.stream(values())
            .filter(value -> PATTERN_START_END.matcher(input).matches())
            .filter(value -> input.equals(value.name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COMMAND_EXCEPTION));
    }

    public static Command MoveStatusEnd(String input) {
        return Arrays.stream(values())
            .filter(value -> PATTERN_END_MOVE.matcher(input).matches())
            .filter(value -> input.startsWith(value.name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COMMAND_EXCEPTION));
    }
}
