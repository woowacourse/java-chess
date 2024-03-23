package domain.command;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import view.CommandType;

public class CommandParser {
    public static final Map<CommandType, Function<List<String>, Command>> commandByChessCommand;

    private CommandParser() {
    }

    static {
        commandByChessCommand = Map.of(
                CommandType.START, StartCommand::new,
                CommandType.END, EndCommand::new,
                CommandType.MOVE, MoveCommand::new
        );
    }

    public static Command parse(List<String> arguments) {
        return commandByChessCommand.get(CommandType.from(arguments.get(0))).apply(parseArguments(arguments));
    }

    private static List<String> parseArguments(List<String> arguments) {
        return arguments.subList(1, arguments.size());
    }
}
