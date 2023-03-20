package chess.view;

import chess.domain.position.Position;

public class CommandDto {

    private final Command command;
    private final Position sourcePosition;
    private final Position targetPosition;

    public CommandDto(final Command command, final Position sourcePosition, final Position targetPosition) {
        this.command = command;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public CommandDto(final Command command) {
        this.command = command;
        this.sourcePosition = new Position(0, 0);
        this.targetPosition = new Position(0, 0);
    }

    public Command getCommand() {
        return command;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
