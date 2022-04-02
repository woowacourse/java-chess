package chess.command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum CommandType {

    START("start", Start::new),
    MOVE("move", Move::new),
    END("end", End::new),
    STATUS("status", Status::new);

    private static final String ERROR_MESSAGE_COMMAND = "[ERROR] 그런 명령어는 없는뎅...ㅎ;;";

    private final String value;
    private final Function<List<String>, Command> of;

    CommandType(String value, Function<List<String>, Command> of) {
        this.value = value;
        this.of = of;
    }

    public static Command getCommand(List<String> value) {
        return Arrays.stream(values())
                .filter(commandType -> commandType.value.equals(value.get(0)))
                .map(commandType -> commandType.of.apply(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static CommandType find(String value) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_COMMAND));
    }
}
