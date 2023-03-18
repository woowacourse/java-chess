package chess.view.request;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum CommandType {
    START(Pattern.compile("^start$")),
    END(Pattern.compile("^end$")),
    MOVE(Pattern.compile("^move [a-h][1-8] [a-h][1-8]$"));

    private final Pattern commandFormat;

    CommandType(Pattern commandFormat) {
        this.commandFormat = commandFormat;
    }

    public static CommandType from(String command) {
        return Arrays.stream(CommandType.values())
                .filter(it -> it.commandFormat.matcher(command).matches())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 커맨드 양식 입니다."));
    }
}
