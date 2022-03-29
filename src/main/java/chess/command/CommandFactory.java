package chess.command;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
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

    private static final Map<String, Supplier<Command>> commands = new HashMap<>();

    static {
        commands.put("start", Start::new);
        commands.put("status", Status::new);
        commands.put("end", End::new);
    }

    private CommandFactory() {

    }

    public static Command find(final String command, final List<String> moveArgs) {
        if (commands.containsKey(command)) {
            return commands.get(command).get();
        }
        if (command.equals("move")) {
            return move(moveArgs);
        }
        throw new IllegalArgumentException();
    }

    private static Command move(final List<String> moveArgs) {
        if (moveArgs.size() != MOVE_ARGUMENTS_SIZE) {
            throw new IllegalArgumentException();
        }
        final Position start = parseStringToPosition(moveArgs.get(START_POSITION_INDEX));
        final Position target = parseStringToPosition(moveArgs.get(TARGET_POSITION_INDEX));
        return new Move(start, target);
    }

    private static Position parseStringToPosition(final String rawPosition) {
        if (rawPosition.length() != POSITION_ARGUMENT_LENGTH) {
            throw new IllegalArgumentException();
        }
        final String[] separatedPosition = rawPosition.split("");
        final Column column = Column.from(separatedPosition[ROW_INDEX]);
        final Row row = Row.from(separatedPosition[COLUMN_INDEX]);
        return new Position(column, row);
    }
}
