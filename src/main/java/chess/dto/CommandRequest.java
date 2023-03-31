package chess.dto;

import java.util.Collections;
import java.util.List;

public class CommandRequest {

    private final String command;
    private final List<Integer> sourceCoordinate;
    private final List<Integer> destinationCoordinate;

    private CommandRequest(final String command, final List<Integer> sourceCoordinate,
                           final List<Integer> destinationCoordinate) {
        this.command = command;
        this.sourceCoordinate = sourceCoordinate;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static CommandRequest fromMoveCommand(final String command,
                                                 final List<Integer> source,
                                                 final List<Integer> destination) {
        return new CommandRequest(command, source, destination);
    }

    public static CommandRequest fromControlCommand(final String command) {
        return new CommandRequest(command, Collections.emptyList(), Collections.emptyList());
    }

    public String getCommand() {
        return command;
    }

    public List<Integer> getSourceCoordinate() {
        return sourceCoordinate;
    }

    public List<Integer> getDestinationCoordinate() {
        return destinationCoordinate;
    }
}
