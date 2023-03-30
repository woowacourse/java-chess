package chess.controller;

import java.util.ArrayList;
import java.util.List;

public class StartCommand {

    private final StartCommandType startCommandType;

    public StartCommand(final StartCommandType startCommandType) {
        this.startCommandType = startCommandType;
    }

    public static StartCommand parse(List<String> commands) {
        commands = new ArrayList<>(commands);
        final StartCommandType type = StartCommandType.from(commands.remove(0));
        return new StartCommand(type);
    }

    public StartCommandType getStartCommandType() {
        return startCommandType;
    }
}
