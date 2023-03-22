package chess.controller.command;

import java.util.Arrays;
import java.util.List;

public enum StartCommandType {

    START(0),
    RESTART(1),
    ;

    private final int parameterSize;

    StartCommandType(final int parameterSize) {
        this.parameterSize = parameterSize;
    }

    public static StartCommandType find(final String input) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 커맨드입니다."));
    }

    public boolean matchSize(final List<String> parameters) {
        return parameters.size() == parameterSize;
    }
}
