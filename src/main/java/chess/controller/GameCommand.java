package chess.controller;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum GameCommand {
    START(Pattern.compile("^start$")),
    END(Pattern.compile("^end$")),
    MOVE(Pattern.compile("^move [a-h][1-8] [a-h][1-8]$")),
    STATUS(Pattern.compile("status"));


    private final Pattern commandFormat;

    GameCommand(Pattern commandFormat) {
        this.commandFormat = commandFormat;
    }

    public static GameCommand from(String command) {
        return Arrays.stream(GameCommand.values())
                .filter(it -> it.commandFormat.matcher(command).matches())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 커맨드 양식 입니다."));
    }
}
