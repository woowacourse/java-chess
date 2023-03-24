package chess.view;

import java.util.List;

public class ChessRequest {
    private final String command;
    private final List<String> parameters;

    public ChessRequest(String command, List<String> parameter) {
        this.command = command;
        this.parameters = parameter;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getParameter() {
        return parameters;
    }
}
