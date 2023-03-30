package chess.controller.command;

import chess.exception.ChessException;
import chess.exception.ExceptionCode;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int COMMAND_PARAMETER_START_INDEX = 1;

    public static final int MOVE_CURRENT_POSITION_INDEX = 0;
    public static final int MOVE_TARGET_POSITION_INDEX = 1;

    private final CommandType type;
    private final List<String> parameters;

    private Command(final CommandType type, final List<String> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public static Command of(List<String> inputValues) {
        final CommandType commandType = CommandType.findBy(inputValues.get(COMMAND_TYPE_INDEX));
        final List<String> commandParameters = new ArrayList<>();
        validateParameterSize(inputValues, commandType);
        for (int index = COMMAND_PARAMETER_START_INDEX; index <= commandType.getRequiredParameterNumber(); index++) {
            commandParameters.add(inputValues.get(index));
        }
        return new Command(commandType, commandParameters);
    }

    private static void validateParameterSize(final List<String> inputValues, final CommandType commandType) {
        if (inputValues.size() - 1 != commandType.getRequiredParameterNumber()) {
            throw new ChessException(ExceptionCode.INVALID_COMMAND_PARAMETER);
        }
    }

    public CommandType getType() {
        return type;
    }

    public List<String> getParameters() {
        return List.copyOf(parameters);
    }
}
