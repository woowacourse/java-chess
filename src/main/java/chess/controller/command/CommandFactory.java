package chess.controller.command;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {
    private static final Map<CommandType, Function<List<String>, Command>> COMMAND_GENERATOR;
    private static final int FIRST_PARAMETER_INDEX = 1;

    static {
        COMMAND_GENERATOR = new EnumMap<>(CommandType.class);
        COMMAND_GENERATOR.put(CommandType.START, ignored -> new StartCommand());
        COMMAND_GENERATOR.put(CommandType.MOVE, MoveCommand::new);
        COMMAND_GENERATOR.put(CommandType.STATUS, ignored -> new StatusCommand());
        COMMAND_GENERATOR.put(CommandType.CLEAR, ignored -> new ClearCommand());
        COMMAND_GENERATOR.put(CommandType.END, ignored -> new EndCommand());
    }

    private CommandFactory() {
        throw new UnsupportedOperationException("인스턴스화 할 수 없는 클래스입니다.");
    }

    public static Command from(final String uncheckedCommand) {
        List<String> commandAndParameters = List.of(uncheckedCommand.split(" "));
        CommandType type = findCommandType(commandAndParameters);
        List<String> parameters = commandAndParameters.subList(FIRST_PARAMETER_INDEX, commandAndParameters.size());
        validateNumberOfParameters(parameters, type);
        return COMMAND_GENERATOR.get(type)
                .apply(parameters);
    }

    private static void validateNumberOfParameters(final List<String> commandAndParameters, final CommandType type) {
        if (commandAndParameters.size() != type.getNumberOfParameters()) {
            throw new IllegalArgumentException("명령어의 형식이 잘못되었습니다.");
        }
    }

    private static CommandType findCommandType(final List<String> commandAndParameters) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.name().toLowerCase().equals(commandAndParameters.get(0)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하는 명령이 아닙니다."));
    }
}
