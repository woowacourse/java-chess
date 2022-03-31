package chess.console.domain;

import java.util.List;

public class CommandRequest {

    private final String command;
    private final List<String> options;

    public CommandRequest(final String command, final List<String> options) {
        this.command = command;
        this.options = options;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getOptions() {
        return options;
    }
}
