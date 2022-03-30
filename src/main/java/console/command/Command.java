package console.command;

import chess.position.Position;

public class Command {

    private final CommandType type;
    private final Position from;
    private final Position to;

    public Command(CommandType type, Position from, Position to) {
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public Command(CommandType type) {
        this(type, null, null);
    }

    public boolean isTypeOf(CommandType type) {
        return this.type == type;
    }

    public Position getFrom() {
        if (from == null) {
            throw new NullPointerException("from이 없습니다.");
        }
        return from;
    }

    public Position getTo() {
        if (to == null) {
            throw new NullPointerException("to가 없습니다.");
        }
        return to;
    }
}
