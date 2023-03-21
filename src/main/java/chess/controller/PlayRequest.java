package chess.controller;

public class PlayRequest {

    private final String command;
    private final String source;
    private final String target;

    public PlayRequest(final String command, final String source, final String target) {
        this.command = command;
        this.source = source;
        this.target = target;
    }

    public String getCommand() {
        return command;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
