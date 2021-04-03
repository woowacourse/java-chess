package chess.controller.dto.response;

public class ResponseDTO {
    private final Long gameId;
    private final String title;
    private final double whitePlayerScore;
    private final double blackPlayerScore;
    private final String currentTurnTeamName;
    private final String beforeTurnTeamName;
    private final boolean isKingDead;
    private final BoardResponseDTO boardResponseDTO;
    private final boolean isEnd;

    public ResponseDTO(GameStatusResponseDTO gameStatusResponseDTO, boolean isKingDead, BoardResponseDTO boardResponseDTO) {
        this.gameId = gameStatusResponseDTO.getGameId();
        this.title = gameStatusResponseDTO.getTitle();
        this.whitePlayerScore = gameStatusResponseDTO.getWhitePlayerScore();
        this.blackPlayerScore = gameStatusResponseDTO.getBlackPlayerScore();
        this.currentTurnTeamName = gameStatusResponseDTO.getCurrentTurnTeamColorName();
        this.beforeTurnTeamName = gameStatusResponseDTO.getBeforeTurnTeamColorName();
        this.isKingDead = isKingDead;
        this.boardResponseDTO = boardResponseDTO;
        this.isEnd = false;
    }

    public ResponseDTO(boolean isEnd) {
        this.gameId = null;
        this.title = null;
        this.whitePlayerScore = 0;
        this.blackPlayerScore = 0;
        this.currentTurnTeamName = null;
        this.beforeTurnTeamName = null;
        this.isKingDead = false;
        this.boardResponseDTO = null;
        this.isEnd = isEnd;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getTitle() {
        return title;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }

    public String getCurrentTurnTeamName() {
        return currentTurnTeamName;
    }

    public String getBeforeTurnTeamName() {
        return beforeTurnTeamName;
    }

    public boolean getIsKingDead() {
        return isKingDead;
    }

    public BoardResponseDTO getBoardResponseDTO() {
        return boardResponseDTO;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
