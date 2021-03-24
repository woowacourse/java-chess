package chess.controller.dto.response;

public class MoveResultResponseDTO {
    private final BoardStatusResponseDTO boardStatusResponseDTO;
    private final boolean isKingDead;
    private final String winnerTeamColorName;

    public MoveResultResponseDTO(BoardStatusResponseDTO boardStatusResponseDTO,
        boolean isKingDead, String winnerTeamColorName) {

        this.boardStatusResponseDTO = boardStatusResponseDTO;
        this.isKingDead = isKingDead;
        this.winnerTeamColorName = winnerTeamColorName;
    }

    public BoardStatusResponseDTO getBoardStatusResponseDTO() {
        return boardStatusResponseDTO;
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public String getWinnerTeamColorName() {
        return winnerTeamColorName;
    }
}
