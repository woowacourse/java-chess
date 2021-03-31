package chess.webdto;

public class MoveRequest {
    private String start;
    private String destination;

    public MoveRequest(String start, String destination) {
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
