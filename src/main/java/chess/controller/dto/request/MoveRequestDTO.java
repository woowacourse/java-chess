package chess.controller.dto.request;


import chess.domain.player.type.TeamColor;

public class MoveRequestDTO {
    private final TeamColor teamColor;
    private final String startPositionInput;
    private final String destinationInput;

    public MoveRequestDTO(TeamColor teamColor, CommandRequestDTO commandRequestDTO) {
        this.teamColor = teamColor;
        startPositionInput = commandRequestDTO.getStartPositionInput();
        destinationInput = commandRequestDTO.getDestinationInput();
    }

    public MoveRequestDTO(TeamColor teamColor, String startPositionInput, String destinationInput) {
        this.teamColor = teamColor;
        this.startPositionInput = startPositionInput;
        this.destinationInput = destinationInput;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public String getStartPositionInput() {
        return startPositionInput;
    }

    public String getDestinationInput() {
        return destinationInput;
    }
}
