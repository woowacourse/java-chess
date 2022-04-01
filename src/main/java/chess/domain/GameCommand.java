package chess.domain;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class GameCommand {

    private static final int RAW_COMMAND_TYPE_INDEX = 0;
    private static final int FROM_PARAMETER_INDEX = 1;
    private static final int TO_PARAMETER_INDEX = 2;

    private static final int FROM_POSITION_INDEX_FOR_MOVE = 0;
    private static final int TO_POSITION_INDEX_FOR_MOVE = 1;

    private final CommandType type;
    private final List<Position> movePositions = new ArrayList<>();

    public GameCommand(final String... rawCommand) {
        type = CommandType.of(rawCommand[RAW_COMMAND_TYPE_INDEX]);
        validateCommand(rawCommand);
        if (type == CommandType.MOVE) {
            movePositions.add(Position.of(rawCommand[FROM_PARAMETER_INDEX]));
            movePositions.add(Position.of(rawCommand[TO_PARAMETER_INDEX]));
        }
    }

    private void validateCommand(final String[] rawCommand) {
        if (type == CommandType.MOVE && rawCommand.length != 3) {
            throw new IllegalArgumentException("해당 타입의 커맨드는 인자가 3개여야합니다.");
        }
        if (CommandType.isSingleCommand(type) && rawCommand.length > 1) {
            throw new IllegalArgumentException("해당 타입의 커맨드는 인자가 하나여야합니다.");
        }
    }

    public boolean isSameCommandType(final CommandType commandType) {
        return type == commandType;
    }

    public Position getFromPosition() {
        if (type == CommandType.MOVE) {
            return movePositions.get(FROM_POSITION_INDEX_FOR_MOVE);
        }
        throw new IllegalStateException("해당 커맨드는 이 작업을 할 수 없습니다.");
    }

    public Position getToPosition() {
        if (type == CommandType.MOVE) {
            return movePositions.get(TO_POSITION_INDEX_FOR_MOVE);
        }
        throw new IllegalStateException("해당 커맨드는 이 작업을 할 수 없습니다.");
    }
}
