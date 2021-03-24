package chess.controller.dto.response;

public class MoveResultResponseDTO {
    private final BoardStatusResponseDTO boardStatusResponseDTO;
    private final boolean isKingDead;
    private final String winnerTeamColor;

    public MoveResultResponseDTO(BoardStatusResponseDTO boardStatusResponseDTO,
        boolean isKingDead, String winnerTeamColor) {

        this.boardStatusResponseDTO = boardStatusResponseDTO;
        this.isKingDead = isKingDead;
        this.winnerTeamColor = winnerTeamColor;
    }

    public BoardStatusResponseDTO getBoardStatusResponseDTO() {
        return boardStatusResponseDTO;
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public String getWinnerTeamColor() {
        return winnerTeamColor;
    }
}
