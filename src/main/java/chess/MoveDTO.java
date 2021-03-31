package chess;

public class MoveDTO {
    private String start;
    private String destination;

    public MoveDTO(String start, String destination) {
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
