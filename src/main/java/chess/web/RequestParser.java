package chess.web;

import java.util.List;

public class RequestParser {

    private static final String INPUT_EMPTY = "입력은 한 글자 이상이어야 합니다.";
    private static final String INPUT_BLANK = "입력 전체가 공백문자여서는 안됩니다.";
    private static final String REGEX = "(command=)|(\r\n)";
    private static final String DELETE_STRING = "";
    private static final String DELIMITER = " ";

    private final List<String> command;

    private RequestParser(List<String> command) {
        validateNotEmpty(command);
        validateNotBlank(command);
        this.command = command;
    }

    public static RequestParser from(String body) {
        return new RequestParser(parse(body));
    }

    private static List<String> parse(String body) {
        return List.of(body.replaceAll(REGEX, DELETE_STRING)
                .split(DELIMITER));
    }

    public List<String> getCommand() {
        return command;
    }

    public String getRoomID() {
        return String.join(DELETE_STRING, command);
    }

    private void validateNotEmpty(List<String> command) {
        if (command.isEmpty()) {
            throw new IllegalStateException(INPUT_EMPTY);
        }
    }

    private void validateNotBlank(List<String> command) {
        if (String.join(DELETE_STRING, command).isBlank()) {
            throw new IllegalArgumentException(INPUT_BLANK);
        }
    }
}
