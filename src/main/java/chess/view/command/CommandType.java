package chess.view.command;

import java.util.Arrays;

public enum CommandType {

    START("start", "^start$"),
    MOVE("move", "^move [a-h][1-8] [a-h][1-8]$"),
    END("end", "^end$")
    ;

    private final String message;
    private final String pattern;

    CommandType(final String message, final String pattern) {
        this.message = message;
        this.pattern = pattern;
    }

    public static CommandType of(final String input) {
        return Arrays.stream(values())
                .filter(commands -> input.matches(commands.pattern))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 명령어입니다. 제시된 명령어를 입력하세요: %s".formatted(input)));
    }

    public String getMessage() {
        return message;
    }
}
