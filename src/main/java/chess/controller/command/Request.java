package chess.controller.command;

import java.util.List;
import java.util.stream.Collectors;

public final class Request {

    private final String command;
    private final List<String> parameters;

    private Request(final String command, final List<String> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public static Request from(List<String> commands) {
        String command = commands.get(0);
        List<String> parameters = commands.stream()
                .skip(1)
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
