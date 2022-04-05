package chess.dto;

public class MoveDTO {
    private final String source;
    private final String destination;

    public MoveDTO(String source, String destination) {
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
