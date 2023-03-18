package chess.controller.command;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int COMMAND_PARAMETER_START_INDEX = 1;

    private final Type type;
    private final List<String> parameters;

    private Command(final Type type, final List<String> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public static Command from(List<String> inputValues) {
        final Type commandType = Type.findBy(inputValues.get(COMMAND_TYPE_INDEX));
        final List<String> commandParameters = new ArrayList<>();
        if (commandType.isParameterAllowed()) {
            commandParameters.addAll(inputValues.subList(COMMAND_PARAMETER_START_INDEX, inputValues.size()));
        }
        return new Command(commandType, commandParameters);
    }

    public boolean is(final Type compareType) {
        return type == compareType;
    }

    public String getParameterAt(final int parameterIndex) {
        return parameters.get(parameterIndex);
    }
}
