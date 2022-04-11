package chess.dto;

public class MoveCommand {

    private final int gameNumber;
    private final String source;
    private final String destination;

    public MoveCommand(int gameNumber, String source, String destination) {
        this.gameNumber = gameNumber;
        this.source = source;
        this.destination = destination;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
