package chess.view.console;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    private static Map<String, Command> CACHE =
            Arrays.stream(values()).collect(Collectors.toMap(Command::getValue, Function.identity()));

    public static Command of(final String value) {
        return Optional
                .ofNullable(CACHE.get(value))
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 Command 입니다."));
    }

    public String getValue() {
        return value;
    }
}
