package chess.domain.command;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    private static final String DELIMITER_COMMAND = " ";
    private static final int COMMAND_POSITION = 0;

    private Map<String, Command> commands;

    public Commands(final Map<String, Command> commands) {
        this.commands = new HashMap<>(commands);
    }

    public Command getIfPresent(String command) {
        return commands.getOrDefault(extractCommand(command), new InvalidCommand());
    }

    private String extractCommand(final String command) {
        return command.split(DELIMITER_COMMAND)[COMMAND_POSITION];
    }

}
