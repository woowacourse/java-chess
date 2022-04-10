package chess.command;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum CommandGenerator {
    START(commandType -> commandType == CommandType.START, commands -> new Start(commands)),
    MOVE(commandType -> commandType == CommandType.MOVE, commands -> new Move(commands)),
    END(commandType -> commandType == CommandType.END, commands -> new End(commands)),
    STATUS(commandType -> commandType == CommandType.STATUS, commands -> new Status(commands)),
    CONTINUE(commandType -> commandType == CommandType.CONTINUE, commands -> new Continue(commands));
    private static final String SPLIT_REGEX = " ";
    private static final int COMMAND_TYPE_INDEX = 0;

    private final Predicate<CommandType> condition;
    private final Function<List<String>, Command> of;

    CommandGenerator(Predicate<CommandType> condition,
        Function<List<String>, Command> of) {
        this.condition = condition;
        this.of = of;
    }

    public static Command generate(String inputCommand) {
        List<String> splitCommands = getCommands(inputCommand);
        CommandType commandType = CommandType.from(splitCommands.get(COMMAND_TYPE_INDEX));
        return Arrays.stream(CommandGenerator.values())
            .filter(command -> command.condition.test(commandType))
            .map(command -> command.of.apply(splitCommands))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    private static List<String> getCommands(String input) {
        String answer = input.trim().toLowerCase(Locale.ROOT);
        return Arrays.stream(answer.split(SPLIT_REGEX))
            .filter(cmd -> !cmd.isBlank())
            .collect(Collectors.toList());
    }
}
