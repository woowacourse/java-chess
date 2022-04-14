package chess.domain.command;

import chess.domain.position.Position;

public class MoveCommand {

    private static final int SOURCE_POSITION_COMMAND_INDEX = 1;
    private static final int TARGET_POSITION_COMMAND_INDEX = 2;

    private final Position from;
    private final Position to;

    public MoveCommand(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public static MoveCommand of(String command) {
        String[] commands = command.split(" ");
        Position from = Position.of(commands[SOURCE_POSITION_COMMAND_INDEX]);
        Position to = Position.of(commands[TARGET_POSITION_COMMAND_INDEX]);
        return new MoveCommand(from, to);
    }

    public static MoveCommand of(String source, String target) {
        Position from = Position.of(source);
        Position to = Position.of(target);
        return new MoveCommand(from, to);
    }

    public Position from() {
        return from;
    }

    public Position to() {
        return to;
    }
}
