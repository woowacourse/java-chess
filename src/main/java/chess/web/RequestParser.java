package chess.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestParser {

    private static final String INPUT_EMPTY = "입력은 한 글자 이상이어야 합니다.";
    private static final String INPUT_BLANK = "입력 전체가 공백문자여서는 안됩니다.";

    private final List<String> command;

    private RequestParser(List<String> command) {
        validateNotEmpty(command);
        validateNotBlank(command);
        this.command = command;
    }

    public static RequestParser from(String body) {
        return new RequestParser(
                Arrays.stream(body.replace("command=", "")
                        .replace("\r\n", "")
                        .split(" ")).collect(Collectors.toList()));
    }

    public List<String> getCommand() {
        return command;
    }

    public String getRoomID() {
        return String.join("", command);
    }

    private void validateNotEmpty(List<String> command) {
        if (command.isEmpty()) {
            throw new IllegalStateException(INPUT_EMPTY);
        }
    }

    private void validateNotBlank(List<String> command) {
        if (String.join("", command).isBlank()) {
            throw new IllegalArgumentException(INPUT_BLANK);
        }
    }
}
