package chess.Controller.command;

import chess.domain.board.Position;
import java.util.Optional;

public class ParsedCommand {

    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final String COMMAND_DISTRIBUTOR = " ";
    private static final int STARTING_POINT = 1;
    private static final int DESTINATION = 2;

    private final Command command;
    private final Optional<Position> start;
    private final Optional<Position> destination;

    public ParsedCommand(final String rawCommand) {
        final String[] splitCommand = rawCommand.split(COMMAND_DISTRIBUTOR, -1);
        command = Command.from(splitCommand[0]);
        start = getPositionFromCommand(splitCommand, STARTING_POINT);
        destination = getPositionFromCommand(splitCommand, DESTINATION);
    }

    private Optional<Position> getPositionFromCommand(final String[] command, final int index) {
        if (command.length < index + 1) {
            return Optional.empty();
        }
        return Optional.of(Position.from(command[index]));
    }

    public Command getCommand() {
        return command;
    }

    public Position getStart() {
        return start.orElseThrow(() -> new IllegalArgumentException(INVALID_MOVING_COMMAND));
    }

    public Position getDestination() {
        return destination.orElseThrow(() -> new IllegalArgumentException(INVALID_MOVING_COMMAND));
    }
}
