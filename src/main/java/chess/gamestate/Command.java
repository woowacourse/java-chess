package chess.gamestate;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public enum Command {

    START(Pattern.compile("start")),
    MOVE(Pattern.compile("move [a-h][1-8] [a-h][1-8]")),
    STATUS(Pattern.compile("status")),
    END(Pattern.compile("end")),
    ;

    private final Pattern commandPattern;

    Command(final Pattern commandPattern) {
        this.commandPattern = commandPattern;
    }

    public static Command toCommand(String input) {
        Objects.requireNonNull(input, "command는 null이 들어올 수 없습니다.");
        return Arrays.stream(values())
                .filter(command -> isMatchPattern(command, input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바른 명령어가 아닙니다."));
    }

    private static boolean isMatchPattern(Command command, String input) {
        return command.commandPattern.matcher(input).find();
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isEnd() {
        return this == END;
    }
}
