package chess.controller.command;

import chess.controller.command.parameter.CommandParameter;
import chess.controller.command.parameter.PositionParameter;
import chess.controller.command.parameter.StartOptionParameter;
import chess.exception.ChessException;
import chess.exception.ExceptionCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int COMMAND_PARAMETER_START_INDEX = 1;

    public static final int MOVE_CURRENT_POSITION_INDEX = 0;
    public static final int MOVE_TARGET_POSITION_INDEX = 1;
    public static final int START_OPTION_INDEX = 0;

    private final CommandType type;
    private final List<CommandParameter> parameters;

    private Command(final CommandType type, final List<CommandParameter> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public static Command of(List<String> inputValues) {
        final CommandType commandType = CommandType.findBy(inputValues.get(COMMAND_TYPE_INDEX));
        final List<CommandParameter> commandParameters = new ArrayList<>();
        validateParameterSize(inputValues, commandType);
        for (int index = COMMAND_PARAMETER_START_INDEX; index <= commandType.getRequiredParameterNumber(); index++) {
            commandParameters.add(parseByType(commandType, inputValues.get(index)));
        }
        return new Command(commandType, commandParameters);
    }

    private static CommandParameter parseByType(final CommandType commandType, final String value) {
        if (commandType == CommandType.MOVE) {
            return PositionParameter.of(value);
        }
        if (commandType == CommandType.START) {
            return StartOptionParameter.of(value);
        }
        return null;
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
        return parameters.stream()
                .map(CommandParameter::getValue)
                .collect(Collectors.toUnmodifiableList());
    }
}
