package chess.beforedb.controller.dto.request;

public class MoveRequestDTO {
    private final String startPosition;
    private final String destination;

    public MoveRequestDTO(String startPosition, String destination) {
        this.startPosition = startPosition;
        this.destination = destination;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public String getDestination() {
        return destination;
    }
}
