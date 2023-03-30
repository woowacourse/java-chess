package chess.controller.command;

import java.util.Collections;
import java.util.List;

public final class Command {

    private final CommandType commandType;
    private final List<Integer> arguments;


    public Command(final CommandType commandType, final List<Integer> arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public Command(final CommandType commandType, final int argument) {
        this.commandType = commandType;
        this.arguments = List.of(argument);
    }

    public Command(final CommandType commandType) {
        this.commandType = commandType;
        this.arguments = Collections.emptyList();
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public List<Integer> getArguments() {
        return arguments;
    }
}
