package chess.dto;

import chess.view.Command;
import java.util.Collections;
import java.util.List;

public class CommandRequest {

    private final Command command;
    private final List<Integer> sourceCoordinate;
    private final List<Integer> destinationCoordinate;

    private CommandRequest(final Command command, final List<Integer> sourceCoordinate,
                           final List<Integer> destinationCoordinate) {
        this.command = command;
        this.sourceCoordinate = sourceCoordinate;
        this.destinationCoordinate = destinationCoordinate;
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

    public List<Integer> getSourceCoordinate() {
        return sourceCoordinate;
    }

    public List<Integer> getDestinationCoordinate() {
        return destinationCoordinate;
    }
}
