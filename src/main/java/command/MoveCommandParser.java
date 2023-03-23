package command;

import static command.MoveCommand.END;
import static command.MoveCommand.MOVE;

import domain.position.Position;
import domain.position.Positions;
import java.util.List;

public final class MoveCommandParser {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_DESTINATION = 2;

    private final MoveCommand moveCommand;
    private final Position source;
    private final Position destination;

    private MoveCommandParser(final MoveCommand moveCommand, final Position source, final Position destination) {
        this.moveCommand = moveCommand;
        this.source = source;
        this.destination = destination;
    }

    public static MoveCommandParser parse(final List<String> commands) {
        if (END.equals(MoveCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 1) {
            return new MoveCommandParser(END, null, null);
        }
        if (MOVE.equals(MoveCommand.from(commands.get(COMMAND_INDEX))) && commands.size() == 3) {
            return new MoveCommandParser(MOVE, Positions.from(commands.get(SOURCE_INDEX)),
                    Positions.from(commands.get(DESTINATION_DESTINATION)));
        }

        throw new IllegalArgumentException("명령은 move source위치 target위치 또는 end로 이어야 합니다.");
    }

    public MoveCommand getMoveCommand() {
        return moveCommand;
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }
}
