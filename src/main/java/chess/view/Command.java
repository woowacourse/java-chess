package chess.view;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private static final Map<String, Command> CACHED_COMMAND = Arrays.stream(values())
            .collect(Collectors.toMap(command -> command.expression, Function.identity()));

    private final String expression;

    Command(String expression) {
        this.expression = expression;
    }

    public static Command from(String expression) {
        if (CACHED_COMMAND.containsKey(expression)) {
            return CACHED_COMMAND.get(expression);
        }
        throw new IllegalArgumentException("입력된 명령을 찾을 수 없습니다.");
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isEnd() {
        return this == END;
    }
}
