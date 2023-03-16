package chess.dto;

import chess.view.Command;
import java.util.Collections;
import java.util.List;

public class CommandRequest {

    private final Command command;
    private final List<Integer> source;
    private final List<Integer> destination;

    private CommandRequest(final Command command, final List<Integer> source,
        final List<Integer> destination) {
        this.command = command;
        this.source = source;
        this.destination = destination;
    }

    public static CommandRequest fromMoveCommand(final List<Integer> source,
        final List<Integer> destination) {
        return new CommandRequest(Command.MOVE, source, destination);
    }

    public static CommandRequest fromControlCommand(final Command command) {
        return new CommandRequest(command, Collections.emptyList(), Collections.emptyList());
    }

    public Command getCommand() {
        return command;
    }

    public List<Integer> getSource() {
        return source;
    }

    public List<Integer> getDestination() {
        return destination;
    }
}
