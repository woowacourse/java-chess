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
                .orElseThrow(() -> new IllegalArgumentException("게임 시작 전에는 start 혹은 restart 만을 입력할 수 있습니다."));
    }

    public boolean matchSize(final List<String> parameters) {
        return parameters.size() == parameterSize;
    }
}
