package chess.web;

public class MoveCommand {

    private final String source;
    private final String destination;

    public MoveCommand(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
