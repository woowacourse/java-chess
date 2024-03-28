package chess.command;

import chess.domain.position.Position;

public class Command {
    private final CommandType commandType;
    private final Position startPosition;
    private final Position targetPosition;

    public Command(final CommandType commandType, final Position startPosition, final Position targetPosition) {
        this.commandType = commandType;
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    public static Command from(CommandType commandType) {
        return new Command(commandType, null, null);
    }

    public CommandType type() {
        return commandType;
    }

    public Position getStartPosition() {
        if (startPosition == null) {
            throw new IllegalArgumentException("startPosition이 비어있습니다.");
        }
        return startPosition;
    }

    public Position getTargetPosition() {
        if (targetPosition == null) {
            throw new IllegalArgumentException("targetPosition이 비어있습니다.");
        }
        return targetPosition;
    }
}
