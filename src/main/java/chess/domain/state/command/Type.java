package chess.domain.state.command;

import java.util.Arrays;
import java.util.List;

public enum Type {

    START(0),
    MOVE(2),
    END(0),
    ;

    private final int parameterSize;

    Type(final int parameterSize) {
        this.parameterSize = parameterSize;
    }

    public static Type find(final String input) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 커맨드입니다."));
    }

    public boolean matchSize(final List<String> parameters) {
        return parameters.size() == parameterSize;
    }
}
