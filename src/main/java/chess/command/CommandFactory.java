package chess.command;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CommandFactory {

    private static final int MOVE_ARGUMENTS_SIZE = 2;
    private static final int START_POSITION_INDEX = 0;
    private static final int TARGET_POSITION_INDEX = 1;
    private static final int POSITION_ARGUMENT_LENGTH = 2;
    private static final int ROW_INDEX = 0;
    private static final int COLUMN_INDEX = 1;
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_ARGUMENT_START_INDEX = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final String COMMAND_DISTRIBUTOR = " ";
    private static final String INVALID_COMMAND = "올바르지 명령입니다.";
    private static final String INVALID_COMMAND_INPUT = "입력에 맞는 명령을 찾을 수 없습니다.";
    private static final String INVALID_MOVING_ARGUMENTS = "잘못된 이동 명령입니다.";

    private static final Map<String, Supplier<Command>> commands = new HashMap<>();

    static {
        commands.put("start", Start::new);
        commands.put("status", Status::new);
        commands.put("end", End::new);
    }

    private CommandFactory() {

    }

    public static Command find(final String commandString) {
        final List<String> args = Arrays.asList(commandString
                .split(COMMAND_DISTRIBUTOR, -1));
        if (args.size() != 1 && args.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException(INVALID_COMMAND);
        }
        return create(args.get(COMMAND_INDEX), args.subList(MOVE_ARGUMENT_START_INDEX, args.size()));
    }

    private static Command create(final String command, final List<String> moveArgs) {
        if (commands.containsKey(command)) {
            return commands.get(command).get();
        }
        if (command.equals("move")) {
            return createMove(moveArgs);
        }
        throw new IllegalArgumentException(INVALID_COMMAND_INPUT);
    }

    private static Command createMove(final List<String> moveArgs) {
        if (moveArgs.size() != MOVE_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException(INVALID_MOVING_ARGUMENTS);
        }
        final Position start = parseStringToPosition(moveArgs.get(START_POSITION_INDEX));
        final Position target = parseStringToPosition(moveArgs.get(TARGET_POSITION_INDEX));
        return new Move(start, target);
    }

    private static Position parseStringToPosition(final String rawPosition) {
        if (rawPosition.length() != POSITION_ARGUMENT_LENGTH) {
            throw new IllegalArgumentException(INVALID_MOVING_ARGUMENTS);
        }
        final String[] separatedPosition = rawPosition.split("");
        final Column column = Column.from(separatedPosition[ROW_INDEX]);
        final Row row = Row.from(separatedPosition[COLUMN_INDEX]);
        return new Position(column, row);
    }
}
