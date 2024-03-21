package chess.view.command;

import java.util.Arrays;

public enum Command {

    START("start", "start"),
    MOVE("move", "^move [a-h][1-8] [a-h][1-8]$"),
    END("end", "end")
    ;

    private final String message;
    private final String pattern;

    Command(final String message, final String pattern) {
        this.message = message;
        this.pattern = pattern;
    }

    public static Command of(final String input) {
        return Arrays.stream(values())
                .filter(command -> input.matches(command.pattern))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 명령어입니다. 제시된 명령어를 입력하세요: %s".formatted(input)));
    }

    public static boolean isStart(final String input) {
        return input.matches(START.pattern);
    }

    public static boolean isEnd(final String input) {
        return input.matches(END.pattern);
    }

    public static boolean isMove(final String input) {
        return input.matches(MOVE.pattern);
    }

    public String getMessage() {
        return message;
    }

}
