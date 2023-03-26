package chess.controller;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum GameCommand {
    END(Pattern.compile("^end$")),
    MOVE(Pattern.compile("^move [a-h][1-8] [a-h][1-8]$")),
    STATUS(Pattern.compile("^status$")),
    SIGNUP(Pattern.compile("^signUp [a-zA-Z0-9]+ [a-zA-Z0-9]+ [a-zA-Z0-9]+$")),
    LOGIN(Pattern.compile("^login [a-zA-Z0-9]+ [a-zA-Z0-9]+$")),
    RESUME(Pattern.compile("^resume [가-힣]+$")),
    CREATE(Pattern.compile("^create [가-힣]+$"));


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
