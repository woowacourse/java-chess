package chess.beforedb.controller.dto.response;

public class ResponseDTO {
    private final BoardResponseDTO boardResponseDTO;
    private final String currentTurnTeamName;
    private final double blackPlayerScore;
    private final double whitePlayerScore;
    private final boolean isKingDead;
    private final String beforeTurnTeamName;
    private boolean isEnd;

    public ResponseDTO(BoardResponseDTO boardResponseDTO, String currentTurnTeamName,
        double blackPlayerScore, double whitePlayerScore, boolean isKingDead,
        String beforeTurnTeamName) {

        this.boardResponseDTO = boardResponseDTO;
        this.currentTurnTeamName = currentTurnTeamName;
        this.blackPlayerScore = blackPlayerScore;
        this.whitePlayerScore = whitePlayerScore;
        this.isKingDead = isKingDead;
        this.beforeTurnTeamName = beforeTurnTeamName;
    }

    public ResponseDTO(boolean isEnd) {
        this.boardResponseDTO = null;
        this.currentTurnTeamName = null;
        this.blackPlayerScore = 0;
        this.whitePlayerScore = 0;
        this.isKingDead = false;
        this.beforeTurnTeamName = null;
        this.isEnd = isEnd;
    }

    public BoardResponseDTO getBoardResponseDTO() {
        return boardResponseDTO;
    }

    public String getCurrentTurnTeamName() {
        return currentTurnTeamName;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }

    public boolean getIsKingDead() {
        return isKingDead;
    }

    public String getBeforeTurnTeamName() {
        return beforeTurnTeamName;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
