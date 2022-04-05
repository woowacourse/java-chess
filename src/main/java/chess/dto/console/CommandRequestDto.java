package chess.dto.console;

import chess.domain.Command;
import chess.domain.position.Position;
import java.util.List;

public class CommandRequestDto {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Command command;
    private final Position from;
    private final Position to;

    public CommandRequestDto(final List<String> commands) {
        this.command = Command.from(find(commands, COMMAND_INDEX));
        this.from = Position.from(find(commands, SOURCE_INDEX));
        this.to = Position.from(find(commands, TARGET_INDEX));
    }

    public String find(final List<String> commands, final int index) {
        try {
            return commands.get(index);
        } catch (final IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Command getCommand() {
        return command;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
