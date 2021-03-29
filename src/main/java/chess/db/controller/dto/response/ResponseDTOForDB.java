package chess.db.controller.dto.response;

import chess.beforedb.controller.dto.response.BoardResponseDTO;

public class ResponseDTOForDB {
    private final BoardResponseDTOForDB boardResponseDTO;
    private final String currentTurnTeamName;
    private final double whitePlayerScore;
    private final double blackPlayerScore;
    private final boolean isKingDead;
    private final String beforeTurnTeamName;
    private boolean isEnd;

    public ResponseDTOForDB(BoardResponseDTOForDB boardResponseDTO, String currentTurnTeamName,
        double whitePlayerScore, double blackPlayerScore, boolean isKingDead,
        String beforeTurnTeamName) {

        this.boardResponseDTO = boardResponseDTO;
        this.currentTurnTeamName = currentTurnTeamName;
        this.whitePlayerScore = whitePlayerScore;
        this.blackPlayerScore = blackPlayerScore;
        this.isKingDead = isKingDead;
        this.beforeTurnTeamName = beforeTurnTeamName;
    }

    public ResponseDTOForDB(boolean isEnd) {
        this.boardResponseDTO = null;
        this.currentTurnTeamName = null;
        this.whitePlayerScore = 0;
        this.blackPlayerScore = 0;
        this.isKingDead = false;
        this.beforeTurnTeamName = null;
        this.isEnd = isEnd;
    }

    public BoardResponseDTOForDB getBoardResponseDTO() {
        return boardResponseDTO;
    }

    public String getCurrentTurnTeamName() {
        return currentTurnTeamName;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
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
