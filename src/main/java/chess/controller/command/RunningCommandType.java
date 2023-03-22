package chess.controller.command;

import java.util.Arrays;
import java.util.List;

public enum RunningCommandType {

    MOVE(2),
    END(0),
    STATUS(0),
    ;

    private final int parameterSize;

    RunningCommandType(final int parameterSize) {
        this.parameterSize = parameterSize;
    }

    public static RunningCommandType find(final String input) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("move, end, status 중 하나의 커멘드를 입력해주세요"));
    }

    public boolean matchSize(final List<String> parameters) {
        return parameters.size() == parameterSize;
    }
}
