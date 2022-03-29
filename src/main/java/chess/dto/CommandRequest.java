package chess.dto;

import chess.game.Command;
import chess.game.Position;

public class CommandRequest {
    private final Command command;
    private Position from;
    private Position to;

    public CommandRequest(final String command) {
        this.command = Command.of(command);
    }

    public CommandRequest(final String command, final String from, final String to) {
        this(command);
        this.from = Position.of(from);
        this.to = Position.of(to);
    }

    public Command getCommandType() {
        return command;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
