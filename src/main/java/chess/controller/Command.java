package chess.controller;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

public enum Command {

    START("start", sting -> List.of()),
    MOVE("move", sting -> List.of(sting.split(" "))),
    END("end", sting -> List.of()),
    STATUS("status", string -> List.of());

    static final String NOT_FOUND_COMMAND_EXCEPTION = "[ERROR] 이 명령어는 존재하지 않습니다.";
    static final String ERROR_NOT_ALLOWED = "[ERROR] 명령어에 접근할 수 없습니다.";

    private static final String REGEX_START_END = "(start)|(end)";
    private static final String REGEX_MOVE_STATUS_END = "(end)|(status)|(move [a-h][1-8] [a-h][1-8])";
    private static final Pattern PATTERN_START_END = Pattern.compile(REGEX_START_END);
    private static final Pattern PATTERN_MOVE_STATUS_END = Pattern.compile(REGEX_MOVE_STATUS_END);
    private static final int INDEX_SOURCE = 1;
    private static final int INDEX_TARGET = 2;

    private final String name;
    private final Function<String, List<String>> function;

    Command(String name, Function<String, List<String>> function) {
        this.name = name;
        this.function = function;
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
            .filter(value -> PATTERN_MOVE_STATUS_END.matcher(input).matches())
            .filter(value -> input.startsWith(value.name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COMMAND_EXCEPTION));
    }

    public String getSource(String input) {
        if (isMove()) {
            return function.apply(input).get(INDEX_SOURCE);
        }
        throw new IllegalStateException(ERROR_NOT_ALLOWED);
    }

    public String getTarget(String input) {
        if (isMove()) {
            return function.apply(input).get(INDEX_TARGET);
        }
        throw new IllegalStateException(ERROR_NOT_ALLOWED);
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isMove() {
        return this == MOVE;
    }
}
