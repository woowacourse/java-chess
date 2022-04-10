package chess.view.consoleview.input;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum CommandType {

    START(Pattern.compile("start")),
    END(Pattern.compile("end")),
    MOVE(Pattern.compile("move [a-h][1-8] [a-h][1-8]")),
    STATUS(Pattern.compile("status"))
    ;

    private final Pattern commandPattern;

    CommandType(final Pattern commandPattern) {
        this.commandPattern = commandPattern;
    }

    public static CommandType from(final String value) {
        return Arrays.stream(values())
                .filter(command -> command.commandPattern
                        .matcher(value)
                        .matches())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("명령어 입력이 잘못되었습니다."));
    }
}
