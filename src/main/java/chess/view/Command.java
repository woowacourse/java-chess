package chess.view;

import java.util.List;

public record Command(CommandType type, List<String> arguments) {

    private static final int REQUIRE_MOVE_ARGUMENTS_COUNT = 2;
    private static final String ERROR_MOVE_ARGUMENTS_COUNT =
            String.format("move 명령어는 인자가 %d개 필요합니다.", REQUIRE_MOVE_ARGUMENTS_COUNT);

    public Command {
        validateMoveArgumentCount(type, arguments);
    }

    public static Command from(List<String> command) {
        return new Command(CommandType.from(command.get(0)), command);
    }

    private void validateMoveArgumentCount(CommandType type, List<String> arguments) {
        if (type == CommandType.MOVE && arguments.size() <= REQUIRE_MOVE_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException(ERROR_MOVE_ARGUMENTS_COUNT);
        }
    }

    public boolean isType(CommandType type) {
        return this.type == type;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
