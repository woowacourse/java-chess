package chess.dto;

public class MoveDto {
    private final String source;
    private final String destination;

    public MoveDto(String source, String destination) {
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
