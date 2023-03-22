package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move");

    private final static int COMMAND_KEYWORD_INDEX = 0;
    private final static int START_AND_END_COMMAND_SIZE = 1;
    private final static int MOVE_COMMAND_SIZE = 3;
    public final static int MOVE_SOURCE_INDEX = 1;
    public final static int MOVE_DESTINATION_INDEX = 2;
    private final static int MOVE_SQUARE_INPUT_SIZE = 2;

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
        if (command.size() == START_AND_END_COMMAND_SIZE) {
            validateStartAndEnd(command.get(COMMAND_KEYWORD_INDEX));
            return;
        }
        if (command.size() == MOVE_COMMAND_SIZE) {
            validateMove(command);
            return;
        }
        throw new CommandException();
    }

    public static void validateStartAndEnd(final String command) {
        if (command.equals(START.keyword) || command.equals(END.keyword)) {
            return;
        }
        throw new CommandException();
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
}
