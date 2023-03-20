package chess.dto;

import chess.view.Command;

public class CommandRequest {

    private static final String EMPTY = "";

    private final Command command;
    private final String source;
    private final String destination;

    private CommandRequest(final Command command, final String source, final String destination) {
        this.command = command;
        this.source = source;
        this.destination = destination;
    }

    public static CommandRequest fromMoveCommand(final String source, final String destination) {
        return new CommandRequest(Command.MOVE, source, destination);
    }

    public static CommandRequest fromControlCommand(final Command command) {
        return new CommandRequest(command, EMPTY, EMPTY);
    }

    public Command getCommand() {
        return command;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

}
