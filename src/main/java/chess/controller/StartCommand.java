package chess.controller;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {

    private final List<String> parameters;
    private final StartCommandType startCommandType;

    private StartCommand(final List<String> parameters, final StartCommandType startCommandType) {
        this.parameters = parameters;
        this.startCommandType = startCommandType;
    }

    public static StartCommand parse(List<String> commands) {
        commands = new ArrayList<>(commands);
        final StartCommandType type = StartCommandType.from(commands.remove(0));
        return new StartCommand(commands, type);
    }

    public List<String> getParameters() {
        return parameters;
    }

    public StartCommandType getStartCommandType() {
        return startCommandType;
    }
}
