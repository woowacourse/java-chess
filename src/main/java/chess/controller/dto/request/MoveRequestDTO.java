package chess.controller.dto.request;


public class MoveRequestDTO {
    private final String teamColor;
    private final String startPositionInput;
    private final String destinationInput;

    public MoveRequestDTO(String teamColor, CommandRequestDTO commandRequestDTO) {
        this.teamColor = teamColor;
        startPositionInput = commandRequestDTO.getStartPositionInput();
        destinationInput = commandRequestDTO.getDestinationInput();
    }

    public String getTeamColor() {
        return teamColor;
    }

    public String getStartPositionInput() {
        return startPositionInput;
    }

    public String getDestinationInput() {
        return destinationInput;
    }
}
