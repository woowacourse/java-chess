package chess.domain;

import java.util.Arrays;

public enum Command {

    START("start"),
    MOVE("move"),
    END("end");

    private final String expression;

    Command(String expression) {
        this.expression = expression;
    }

    public static Command from(String expression) {
        return Arrays.stream(values())
                .filter(command -> command.isExpressionMatch(expression))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("start, end, move 명령만 입력할 수 있습니다."));
    }

    private boolean isExpressionMatch(String expression) {
        return expression.startsWith(this.expression);
    }

    public boolean isEnd() {
        return this == END;
    }
}
