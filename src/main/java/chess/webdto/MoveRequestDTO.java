package chess.webdto;

public class MoveRequestDTO {
    private final String start;
    private final String destination;

    public MoveRequestDTO(String start, String destination) {
        this.start = start;
        this.destination = destination;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }
}
