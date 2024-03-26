package domain.command;

import domain.Position;

public class MoveCommand implements Command {
    private final Position from;
    private final Position to;

    public MoveCommand(String from, String to) {
        this.from = Position.valueOf(from.toUpperCase());
        this.to = Position.valueOf(to.toUpperCase());
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
