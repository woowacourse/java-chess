package chess.controller.dto.request;

public class MoveRequestDTO {
    private final String startPositionInput;
    private final String destinationInput;

    public MoveRequestDTO(String startPositionInput, String destinationInput) {
        this.startPositionInput = startPositionInput;
        this.destinationInput = destinationInput;
    }

    public String getStartPositionInput() {
        return startPositionInput;
    }

    public String getDestinationInput() {
        return destinationInput;
    }
}
