package chess.view;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {

    START("^start$"),
    MOVE("^move [a-h][1-8] [a-h][1-8]$"),
    END("^end$");

    private static final String ERROR_INVALID_COMMAND = " 은(는) 올바르지 않은 명령어 입니다.";

    private final Pattern format;

    Command(final String format) {
        this.format = Pattern.compile(format);
    }

    public static Command from(final String input) {
        return Arrays.stream(values())
                .filter(command -> command.format.matcher(input).matches())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(input + ERROR_INVALID_COMMAND));
    }

    public static void validateFormat(final String input) {
        boolean isInValidFormat = Arrays.stream(values())
                .noneMatch(command -> command.format.matcher(input).matches());
        if (isInValidFormat) {
            throw new IllegalArgumentException(input + ERROR_INVALID_COMMAND);
        }
    }

    public boolean isStart() {
        return this == Command.START;
    }

    public boolean isMove() {
        return this == Command.MOVE;
    }

    public boolean isEnd() {
        return this == Command.END;
    }

    public boolean isNotEnd() {
        return this != Command.END;
    }
}
