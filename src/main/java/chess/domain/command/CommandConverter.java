package chess.domain.command;

import chess.domain.position.Position;
import java.util.List;

public class CommandConverter {

    private static final String INVALID_INPUT = "유효한 입력이 아닙니다.";
    private static final String START_PREFIX = "start";
    private static final String END_PREFIX = "end";
    private static final String STATUS_PREFIX = "status";
    private static final String MOVE_PREFIX = "move";

    private static final int PREFIX_INDEX = 0;
    private static final int FROM_INDEX = 1;
    private static final int TO_INDEX = 2;
    private static final int SINGLE_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int POSITION_SIZE = 2;

    private static Command generateSingleCommand(String prefix) {
        if (prefix.equals(START_PREFIX)) {
            return SingleCommand.START;
        }
        if (prefix.equals(STATUS_PREFIX)) {
            return SingleCommand.STATUS;
        }
        if (prefix.equals(END_PREFIX)) {
            return SingleCommand.END;
        }
        throw new IllegalArgumentException(INVALID_INPUT);
    }

    private static Command generateMultipleCommand(List<String> input) {
        String prefix = input.get(PREFIX_INDEX);
        String from = input.get(FROM_INDEX);
        String to = input.get(TO_INDEX);
        if (!prefix.equals(MOVE_PREFIX) || from.length() != POSITION_SIZE || to.length() != POSITION_SIZE) {
            throw new IllegalArgumentException();
        }
        return new MoveCommand(new Position(from), new Position(to));
    }

    public static Command convertCommand(List<String> input) {
        if (input.size() == SINGLE_COMMAND_SIZE) {
            return generateSingleCommand(input.get(PREFIX_INDEX));
        }
        if (input.size() == MOVE_COMMAND_SIZE) {
            return generateMultipleCommand(input);
        }
        throw new IllegalArgumentException(INVALID_INPUT);
    }
}
