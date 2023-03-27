package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),
    LOAD("load"),
    SAVE("save");

    private static final int COMMAND_KEYWORD_INDEX = 0;
    private static final int NORMAL_COMMAND_SIZE = 1;
    private static final int LOAD_COMMAND_SIZE = 2;
    private static final int MOVE_COMMAND_SIZE = 3;
    public static final int LOAD_ID_INDEX = 1;
    public static final int MOVE_SOURCE_INDEX = 1;
    public static final int MOVE_DESTINATION_INDEX = 2;
    private static final int MOVE_SQUARE_INPUT_SIZE = 2;

    private final String keyword;

    Command(final String keyword) {
        this.keyword = keyword;
    }

    public static Command from(final List<String> input) {
        validateCommand(input);
        return Arrays.stream(values())
                .filter(command -> Objects.equals(command.keyword, input.get(COMMAND_KEYWORD_INDEX)))
                .findFirst()
                .orElseThrow(CommandException::new);
    }

    private static void validateCommand(final List<String> command) {
        if (command.size() == NORMAL_COMMAND_SIZE) {
            validateNormalCommand(command.get(COMMAND_KEYWORD_INDEX));
            return;
        }
        if (command.size() == LOAD_COMMAND_SIZE) {
            validateLoad(command);
            return;
        }
        if (command.size() == MOVE_COMMAND_SIZE) {
            validateMove(command);
            return;
        }
        throw new CommandException();
    }

    public static void validateNormalCommand(final String command) {
        if (isNormalKeyword(command)) {
            return;
        }
        throw new CommandException();
    }

    private static boolean isNormalKeyword(final String command) {
        if (START.keyword.equals(command)) {
            return true;
        }
        if (END.keyword.equals(command)) {
            return true;
        }
        return STATUS.keyword.equals(command);
    }

    private static void validateLoad(final List<String> command) {
        final String loadCommand = command.get(COMMAND_KEYWORD_INDEX);
        if (!loadCommand.equals(LOAD.keyword)) {
            throw new CommandException();
        }
        validateLoadId(command.get(LOAD_ID_INDEX));
    }

    private static void validateLoadId(final String loadId) {
        try {
            Integer.parseInt(loadId);
        } catch (NumberFormatException e) {
            throw new CommandException();
        }
    }

    private static void validateMove(final List<String> command) {
        final String moveCommand = command.get(COMMAND_KEYWORD_INDEX);
        if (!moveCommand.equals(MOVE.keyword)) {
            throw new CommandException();
        }
        validateSquareInput(command.get(MOVE_SOURCE_INDEX));
        validateSquareInput(command.get(MOVE_DESTINATION_INDEX));
    }

    private static void validateSquareInput(final String squareInput) {
        if (squareInput.length() != MOVE_SQUARE_INPUT_SIZE) {
            throw new CommandException();
        }
    }

    public boolean isStartCommand() {
        return this == START;
    }

    public boolean isEndCommand() {
        return this == END;
    }

    public boolean isMoveCommand() {
        return this == MOVE;
    }

    public boolean isStatusCommand() {
        return this == STATUS;
    }

    public boolean isLoadCommand() { return this == LOAD; }
}
