package chess.console.gamestate;

import chess.domain.Position;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public enum Command {

    START(Pattern.compile("start")),
    MOVE(Pattern.compile("move [a-h][1-8] [a-h][1-8]")),
    STATUS(Pattern.compile("status")),
    END(Pattern.compile("end")),
    ;

    private static final int MOVE_SOURCE_POSITION_INDEX = 1;
    private static final int MOVE_TARGET_POSITION_INDEX = 2;

    private final Pattern commandPattern;

    Command(Pattern commandPattern) {
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

    public MovePosition movePosition(String command) {
        validateCanCalculateCommand();
        List<String> values = Arrays.asList(command.split(" "));
        Position source = Position.from(values.get(MOVE_SOURCE_POSITION_INDEX));
        Position target = Position.from(values.get(MOVE_TARGET_POSITION_INDEX));
        return new MovePosition(source, target);
    }

    private void validateCanCalculateCommand() {
        if (!isMove()) {
            throw new IllegalStateException("position을 계산할 수 있는 Command가 아닙니다.");
        }
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
