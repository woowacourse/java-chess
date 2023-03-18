package chess.view.request;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum CommandType {
    START(Pattern.compile("^start$"), true),
    END(Pattern.compile("^end$"), false),
    MOVE(Pattern.compile("^move [a-h][1-8] [a-h][1-8]$"), true);

    private final Pattern commandFormat;
    private final boolean isRunning;

    CommandType(Pattern commandFormat, boolean isRunning) {
        this.commandFormat = commandFormat;
        this.isRunning = isRunning;
    }

    public static CommandType from(String command) {
        return Arrays.stream(CommandType.values())
                .filter(it -> it.commandFormat.matcher(command).matches())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 커맨드 양식 입니다."));
    }

    public boolean isRunning() {
        return isRunning;
    }
}
