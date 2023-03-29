package chess.command;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;

public class CommandFactory2 {
    
    private static final EnumMap<CommandType, Function<List<String>, Command2>> COMMANDS = new EnumMap<>(
            CommandType.class);
    
    static {
        COMMANDS.put(CommandType.START, StartCommand2::new);
        COMMANDS.put(CommandType.MOVE, MoveCommand2::new);
        COMMANDS.put(CommandType.END, EndCommand2::new);
        COMMANDS.put(CommandType.STATUS, StatusCommand2::new);
    }
    
    public static Command2 generateCommand(List<String> tokens) {
        CommandType commandType = CommandType.from(tokens.get(0));
        List<String> arguments = tokens.subList(1, tokens.size());
        return COMMANDS.get(commandType).apply(arguments);
    }
    
}
