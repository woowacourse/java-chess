package domain.command;

import domain.position.Position;

public class PlayCommand {

    private static final PositionCommand EMPTY_POSITION = new PositionCommand();

    private final Command command;
    private final PositionCommand positions;

    public PlayCommand(Command command, PositionCommand positions) {
        this.command = command;
        this.positions = positions;
    }

    public PlayCommand(Command command) {
        this(command, EMPTY_POSITION);
    }

    public boolean isMove() {
        return command.isMove();
    }

    public Position sourcePosition() {
        return positions.sourcePosition();
    }

    public Position targetPosition() {
        return positions.targetPosition();
    }
}
