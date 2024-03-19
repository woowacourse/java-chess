package chess.domain;

import java.util.Arrays;
import java.util.Map;

public enum Command {

    START("start"),
    END("end");

    private final String expression;

    Command(String expression) {
        this.expression = expression;
    }

    public static Command from(String expression) {
        return Arrays.stream(values())
                .filter(command -> command.isExpressionMatch(expression))
                .findFirst()
                .orElseThrow();
    }

    private boolean isExpressionMatch(String expression) {
        return this.expression.equals(expression);
    }
}
