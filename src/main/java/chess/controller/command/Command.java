package chess.controller.command;

import chess.controller.command.exception.CommandException;
import chess.controller.command.exception.InvalidCommandMethodException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final int COMMAND_TYPE_SKIP_POINT = 1;
    private static final int LOAD_ID_INDEX = 0;
    private static final int MOVE_SOURCE_INDEX = 0;
    private static final int MOVE_DESTINATION_INDEX = 1;
    private static final int MOVE_SQUARE_INPUT_SIZE = 2;

    private final CommandType commandType;
    private final List<String> parameters;

    private Command(final CommandType commandType, final List<String> parameters) {
        this.commandType = commandType;
        this.parameters = parameters;
    }

    public static Command from(final List<String> input) {
        final CommandType commandType = CommandType.from(input);
        final List<String> parameters = input.stream()
                .skip(COMMAND_TYPE_SKIP_POINT)
                .collect(Collectors.toUnmodifiableList());
        return new Command(commandType, parameters);
    }

    public static Command start() {
        return new Command(CommandType.START, Collections.emptyList());
    }

    public int getLoadId() {
        try {
            validateLoad();
            final String loadIdInput = parameters.get(LOAD_ID_INDEX);
            return Integer.parseInt(loadIdInput);
        } catch (NumberFormatException e) {
            throw new CommandException();
        }
    }

    private void validateLoad() {
        if (CommandType.LOAD != commandType) {
            throw new InvalidCommandMethodException();
        }
    }

    public String getMoveSource() {
        validateMove();
        final String moveSource = parameters.get(MOVE_SOURCE_INDEX);
        validateSquareInput(moveSource);
        return moveSource;
    }

    public String getMoveDestination() {
        validateMove();
        final String moveDestination = parameters.get(MOVE_DESTINATION_INDEX);
        validateSquareInput(moveDestination);
        return moveDestination;
    }

    public void validateMove() {
        if (CommandType.MOVE != commandType) {
            throw new InvalidCommandMethodException();
        }
    }

    private void validateSquareInput(final String squareInput) {
        if (squareInput.length() != MOVE_SQUARE_INPUT_SIZE) {
            throw new CommandException();
        }
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public boolean isStartCommand() {
        return CommandType.START == commandType;
    }

    public boolean isEndCommand() {
        return CommandType.END == commandType;
    }
}
