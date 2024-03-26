package domain.command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move");
    private final String command;

    CommandType(final String command) {
        this.command = command;
    }

    public static final int COMMAND_INDEX = 0;
    public static final Map<CommandType, CommandConstructor> commandByChessCommand;

    static {
        commandByChessCommand = Map.of(
                CommandType.START, StartCommand::new,
                CommandType.END, EndCommand::new,
                CommandType.MOVE, MoveCommand::new
        );
    }

    public static Command parse(List<String> arguments) {
        return commandByChessCommand.get(CommandType.from(arguments.get(COMMAND_INDEX)))
                .generate(parseArguments(arguments));
    }

    private static CommandType from(String command) {
        return Arrays.stream(CommandType.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게임 명령어입니다."));
    }

    private static List<String> parseArguments(List<String> arguments) {
        return arguments.subList(1, arguments.size());
    }
}

