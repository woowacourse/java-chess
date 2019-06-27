package chess;

import java.util.ArrayList;
import java.util.List;

public class InputCommand {
    private final String command;
    private final List<String> arguments = new ArrayList<>();

    public InputCommand(final String command, final List<String> arguments) {
        this.command = command;
        this.arguments.addAll(arguments);
    }

    public String getCommand() {
        return command;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
