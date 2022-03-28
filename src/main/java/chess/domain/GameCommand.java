package chess.domain;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum GameCommand {
    START(Pattern.compile("start")),
    END(Pattern.compile("end")),
    MOVE(Pattern.compile("move [a-h][1-8] [a-h][1-8]")),
    STATUS(Pattern.compile("status"));

    private final Pattern commandPattern;

    GameCommand(Pattern commandPattern) {
        this.commandPattern = commandPattern;
    }

    public static GameCommand of(String inputValue) {
        return Arrays.stream(values())
                .filter(gameCommand -> isMatch(gameCommand, inputValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 명령어를 지원하지 않습니다."));
    }

    private static boolean isMatch(GameCommand gameCommand, String input) {
        return gameCommand.commandPattern.matcher(input).matches();
    }

    boolean isStart() {
        return this == START;
    }

    boolean isEnd() {
        return this == END;
    }

    boolean isMove() {
        return this == MOVE;
    }

    boolean isStatus() {
        return this == STATUS;
    }
}
