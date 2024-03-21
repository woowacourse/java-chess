package chess.view.command;

import java.util.Arrays;

public enum Command {

    START("start", "^start"),
    MOVE("move", "^move [a-h][1-8] [a-h][1-8]$"),
    END("end", "^end")
    ;

    private final String message;
    private final String pattern;

    Command(final String message, final String pattern) {
        this.message = message;
        this.pattern = pattern;
    }

    public static boolean isStart(final String input) {
        return input.matches(START.pattern);
    }

    public static boolean isMove(final String input) {
        return input.matches(MOVE.pattern);
    }

    public String getMessage() {
        return message;
    }

}
