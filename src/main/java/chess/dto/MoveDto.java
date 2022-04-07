package chess.dto;

public class MoveDto {

    private final String currentPosition;
    private final String destinationPosition;

    public MoveDto(String currentPosition, String destinationPosition) {
        this.currentPosition = currentPosition;
        this.destinationPosition = destinationPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public String getDestinationPosition() {
        return destinationPosition;
    }
}
