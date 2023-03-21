package chess.command;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;

public class CommandFactory {
    
    private static final EnumMap<CommandType, Function<List<String>, Command>> COMMANDS = new EnumMap<>(
            CommandType.class);
    
    static {
        COMMANDS.put(CommandType.START, StartCommand::new);
        COMMANDS.put(CommandType.MOVE, MoveCommand::new);
        COMMANDS.put(CommandType.END, EndCommand::new);
    }
    
    public static Command generateCommand(List<String> tokens) {
        CommandType commandType = CommandType.from(tokens.get(0));
        List<String> arguments = tokens.subList(1, tokens.size());
        return COMMANDS.get(commandType).apply(arguments);
    }
    
}
