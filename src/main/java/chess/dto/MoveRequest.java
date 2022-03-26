package chess.dto;

import chess.Command;
import chess.Position;

public class MoveRequest {

    private final Command command;
    private final Position source;
    private final Position target;

    public MoveRequest(Command command, Position source, Position target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public MoveRequest(Command command) {
        this.command = command;
        this.source = null;
        this.target = null;
    }

    public Command getCommand() {
        return command;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
