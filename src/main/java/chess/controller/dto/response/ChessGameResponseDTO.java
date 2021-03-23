package chess.controller.dto.response;

public class ChessGameResponseDTO {
    private BoardStatusResponseDTO boardStatusResponseDTO;
    private MoveResultResponseDTO moveResultResponseDTO;
    private ScoresResponseDTO scoresResponseDTO;
    private boolean isGameEnd;

    public ChessGameResponseDTO(BoardStatusResponseDTO boardStatusResponseDTO) {
        this.boardStatusResponseDTO = boardStatusResponseDTO;
    }

    public ChessGameResponseDTO(MoveResultResponseDTO moveResultResponseDTO) {
        this.moveResultResponseDTO = moveResultResponseDTO;
        isGameEnd = moveResultResponseDTO.isKingDead();
    }

    public ChessGameResponseDTO(ScoresResponseDTO scoresResponseDTO) {
        this.scoresResponseDTO = scoresResponseDTO;
    }

    public ChessGameResponseDTO(boolean isGameEnd) {
        this.isGameEnd = isGameEnd;
    }

    public BoardStatusResponseDTO getBoardStatusResponseDTO() {
        return boardStatusResponseDTO;
    }

    public MoveResultResponseDTO getMoveResultResponseDTO() {
        return moveResultResponseDTO;
    }

    public ScoresResponseDTO getScoresResponseDTO() {
        return scoresResponseDTO;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }
}
