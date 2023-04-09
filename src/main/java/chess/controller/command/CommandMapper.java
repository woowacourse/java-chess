package chess.controller.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class CommandMapper {

    static final int COMMAND_INDEX = 0;
    private static final CommandMapper INSTANCE = new CommandMapper();

    private static final Map<String, Function<List<String>, Command>> commandFactoryMethodSelector = new HashMap<>();

    static {
        commandFactoryMethodSelector.put(LoadCommand.COMMAND, LoadCommand::of);
        commandFactoryMethodSelector.put(StartCommand.COMMAND, StartCommand::of);
        commandFactoryMethodSelector.put(MoveCommand.COMMAND, MoveCommand::of);
        commandFactoryMethodSelector.put(StatusCommand.COMMAND, StatusCommand::of);
        commandFactoryMethodSelector.put(EndCommand.COMMAND, EndCommand::of);
    }

    private CommandMapper() {
    }

    public static CommandMapper getInstance() {
        return INSTANCE;
    }

    public Command getCommand(List<String> userInput) {
        validateCommand(userInput);
        return commandFactoryMethodSelector.get(userInput.get(COMMAND_INDEX))
                                           .apply(userInput);
    }

    private void validateCommand(final List<String> userInput) {
        validateLength(userInput);
        validateCommandExistence(userInput.get(COMMAND_INDEX));
    }

    private void validateLength(final List<String> userInput) {
        if (userInput.isEmpty()) {
            throw new IllegalArgumentException("커맨드로 한 개 이상의 문자를 입력해야 합니다");
        }
    }

    private void validateCommandExistence(final String command) {
        if (!commandFactoryMethodSelector.containsKey(command)) {
            throw new IllegalArgumentException("존재하지 않는 명령어 입니다");
        }
    }
}
