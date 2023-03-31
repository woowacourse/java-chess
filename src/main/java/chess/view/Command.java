package chess.view;

import java.util.Arrays;

public enum Command {

    START(1), END(1), MOVE(3), STATUS(1);

    private final int parameterCount;

    Command(int parameterCount) {
        this.parameterCount = parameterCount;
    }

    public static Command from(String input) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적절하지 않은 명령어입니다"));
    }

    public boolean hasParameterCount(int count) {
        return parameterCount == count;
    }
}
