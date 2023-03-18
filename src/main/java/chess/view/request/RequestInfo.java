package chess.view.request;

import java.util.Arrays;
import java.util.List;

public class RequestInfo {

    private final CommandType commandType;
    private final List<String> commands;

    public RequestInfo(String commands) {
        commandType = CommandType.from(commands);
        this.commands = Arrays.asList(commands.split(" ", -1));
    }

    public List<String> getCommands() {
        return commands;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public boolean isRunning() {
        return commandType.isRunning();
    }
}
