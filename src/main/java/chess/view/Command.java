package chess.view;

import java.util.List;

public record Command(CommandType type, List<String> arguments) {

    private static final String ERROR_ARGUMENTS_COUNT_FORMAT = "%s 명령어는 인자가 %d개 필요합니다.";

    public Command {
        validateArgumentCount(type, arguments);
    }

    public static Command from(List<String> command) {
        return new Command(CommandType.from(command.get(0)), command);
    }

    private void validateArgumentCount(CommandType type, List<String> arguments) {
        int argumentCount = type.getArgumentCount();
        if (arguments.size() - 1 != argumentCount) {
            String commandName = arguments.get(0);
            throw new IllegalArgumentException(ERROR_ARGUMENTS_COUNT_FORMAT.formatted(commandName, argumentCount));
        }
    }

    public boolean isType(CommandType type) {
        return this.type == type;
    }

    public String getArgument(int index) {
        return arguments.get(index);
    }
}
