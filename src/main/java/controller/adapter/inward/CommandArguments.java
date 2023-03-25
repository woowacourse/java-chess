package controller.adapter.inward;

import java.util.List;

public class CommandArguments {

    public static final int START_COORDINATE_INDEX = 0;
    public static final int END_COORDINATE_INDEX = 1;

    private final List<String> arguments;

    private CommandArguments(final List<String> arguments) {
        this.arguments = arguments;
    }

    public static CommandArguments of(final List<String> arguments) {
            return new CommandArguments(arguments);
    }

    public String getRawStartCoordinate() {
        return arguments.get(START_COORDINATE_INDEX);
    }

    public String getRawEndCoordinate() {
        return arguments.get(END_COORDINATE_INDEX);
    }
}
