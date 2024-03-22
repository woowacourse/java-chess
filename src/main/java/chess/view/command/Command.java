package chess.view.command;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {

    START("start", Pattern.compile("start")),
    MOVE("move", Pattern.compile("^move [a-h][1-8] [a-h][1-8]$")),
    END("end", Pattern.compile("end"));

    private final String message;
    private final Pattern pattern;

    Command(final String message, final Pattern pattern) {
        this.message = message;
        this.pattern = pattern;
    }

    public static Command of(final String input) {
        return Arrays.stream(values())
                .filter(command -> command.pattern.matcher(input).matches())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 명령어입니다. 제시된 명령어를 입력하세요: %s".formatted(input)));
    }

    public static boolean isStart(final String input) {
        return START.pattern.matcher(input).matches();
    }

    public static boolean isEnd(final String input) {
        return END.pattern.matcher(input).matches();
    }

    public static boolean isMove(final String input) {
        return MOVE.pattern.matcher(input).matches();
    }

    public String getMessage() {
        return message;
    }

}
