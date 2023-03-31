package chess.controller.command;

import java.util.List;
import java.util.stream.Collectors;

public final class Request {

    private static final int COMMAND_NAME_INDEX = 0;
    private static final int SKIP_COMMAND_NAME = 1;

    private final String command;
    private final List<String> parameters;

    private Request(final String command, final List<String> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public static Request from(List<String> commands) {
        String command = commands.get(COMMAND_NAME_INDEX);
        List<String> parameters = commands.stream()
                .skip(SKIP_COMMAND_NAME)
                .collect(Collectors.toList());

        return new Request(command, parameters);
    }

    public String getCommand() {
        return command;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
